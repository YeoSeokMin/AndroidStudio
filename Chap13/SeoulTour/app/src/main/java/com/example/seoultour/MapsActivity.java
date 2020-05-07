package com.example.seoultour;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    Spinner spSeoul;
    String seoul[]={ "국립중앙박물관","남산골 한옥마을 ", "예술의 전당 ", "청계천   ", "63빌딩 ", "남산타워 ", "경복궁", "김치문화체험관 ", "서울올림픽기념관 ", "국립민속박물관 ", "서대문형무소역사관 ", "창덕궁 "};
    Double lat[]={ 37.5240867,37.5591447, 37.4785361, 37.5696512, 37.5198158, 37.5511147, 37.5788408, 37.5629457, 37.5202976, 37.5815645, 37.5742887, 37.5826041};
    Double lng[]={126.9803881,126.9936826,127.0107423, 127.0056375, 126.9403139, 126.9878596,126.9770162, 126.9851652, 127.1159236,126.9789313,126.9562269,126.9919376};

    Double tourLatLng[]=new Double[2];      //관광지의 위도정보
    Double myLatLng[] = new Double[2];      //내위치의 위도경도
    int pos;

    FusedLocationProviderApi providerApi;
    GoogleApiClient apiClien;

    //내위치정보
    /*LocationManager mylocationManager;
    LocationListener mylistener;*/
    boolean myCheck;        //관광지 찾을것인지 내위치 찾을것인지 선택변수
    int pCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        spSeoul = (Spinner)findViewById(R.id.spSeoul);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,seoul);
        spSeoul.setAdapter(adapter);

        //permission
        pCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);

        if(pCheck == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
        } else {
            myLocationService();
        }


        spSeoul.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                tourLatLng[0]=lat[position];
                tourLatLng[1]=lng[position];
                pos = position;
                myCheck = false;
                tourMove(tourLatLng);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiClien.connect(); //위도경도를 가져온다.



        /*pCheck = ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        //Log.e("MapsActivity","pCheck"+PackageManager.PERMISSION_DENIED);
        if(pCheck==PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
        } else {
            mylocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1,1000,mylistener);
        }*/
    }

    //permission check 후 오는 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //여러개면 if를 사용해서 일일이 해줘야 함.
        switch (requestCode) {
            case 100:
                myLocationService();
                break;
        }
        Log.i("MyApp",""+requestCode);
    }
    //내위치를 알려줘.~~~~~~~
    void myLocationService() {
        apiClien = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        providerApi = LocationServices.FusedLocationApi;//기계정보서비스를 통해서 내정보를 알려고 합니다.
        //callback Method로 onConnedted로 감.

        /*mylocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //Log.i("MyApp",""+mylocationManager);
        //mylocationManager.getProvider(LocationManager.NETWORK_PROVIDER);      //네트워크를 통해서 얻어오겠다.
        //mylocationManager.getProvider(LocationManager.GPS_PROVIDER);          //GPS를 통해서 얻어오겠다
        //mylocationManager.getBestProvider(new Criteria(),true);     //네트워크와 GPS중에서 둘 중에 빠른것을 알아서 판단해서 선택함.





        mylistener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                myLatLng[0] = location.getLatitude();   //위도
                myLatLng[1] = location.getLongitude();  //경도

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                //상태가 바뀔 때마다 옴
                switch (status) {
                    case LocationProvider.OUT_OF_SERVICE:
                        //서비스지역 벗어났을때
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        //일시적 불능
                        break;
                    case LocationProvider.AVAILABLE:
                        //일시적 불능에서 정상으로 돌아왔을때
                        break;
                }
            }

            @Override
            public void onProviderEnabled(String provider) {
                //지금은 쓰지 않을거지만 사용가능 (현재 내위치서비스를 사용할 수 있습니다.)
                //
                //지금은 쓰지 않을거지만 사용불가능 (현재 내위치서비스를 사용할 수 없습니다.)
            }

            @Override
            public void onProviderDisabled(String provider) {
                //현재 내 위치 서비스 불가능
            }
        };*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    //지도 서비스
    public void tourMove(Double locationLatLng[]){

        String[] tourAddress={"서울특별시 용산구 서빙고로 137",
                "서울특별시 중구 퇴계로34길 28",
                "서울특별시 서초구 남부순환로 2364",
                "서울특별시 종로구 창신동","서울특별시 영등포구 63로 50",
                "서울특별시 용산구 남산공원길 105",
                "서울특별시 종로구 삼청로 37","서울특별시 중구 명동2가 32-2",
                "서울특별시 송파구 올림픽로 448",
                "서울특별시 종로구 삼청로 37","서울특별시 서대문구 통일로 251",
                "서울특별시 종로구 율곡로 99"};
        final String[] tourTel = {"02-2077-9000","02-2264-4412",
                "02-580-1300","02-2290-6114","02-789-5663",
                "02-3455-9277","02-3700-3900","02-318-7051",
                "02-410-1354","02-3704-3114","02-360-8590",
                "02-762-8261"};
        final String[] tourHomePage = {"http://www.museum.go.kr",
                "http://hanokmaeul.seoul.go.kr",
                "http://www.sac.or.kr",
                "http://www.cheonggyecheon.or.kr",
                "http://www.63.co.kr",
                "http://www.nseoultower.com",
                "http://www.royalpalace.go.kr",
                "http://www.visitseoul.net/kr/article/article.do?_method=view&art_id=49160&lang=kr&m=0004003002009&p=03",
                "http://www.88olympic.or.kr",
                "http://www.nfm.go.kr",
                "http://www.sscmc.or.kr/culture2",
                "http://www.cdg.go.kr"
        };
        LatLng tourPos=new LatLng(locationLatLng[0],locationLatLng[1]);
        //mMap.addMarker(new MarkerOptions().position(tourPos).title(seoul[pos]));        //지도의 내위치를 표시해주는 Marker
        //기본적으로 제공해주는 Marker를안쓴다.
        //직접 marker를 제작한다.
        MarkerOptions markerOpt = new MarkerOptions();
        markerOpt.position(tourPos);
        markerOpt.icon(BitmapDescriptorFactory.fromResource(R.drawable.point));
        if(myCheck==true) {
            markerOpt.title("현재 내가 서있는 곳");
            markerOpt.snippet("위도:"+locationLatLng[0]+"경도:"+locationLatLng[1]);
        } else {
            markerOpt.title(seoul[pos]);      //pos:전역변수로 선언한
            markerOpt.snippet(tourAddress[pos]+"("+tourTel[pos]+")");

        }

        mMap.addMarker(markerOpt).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tourPos,15));//점점확대되는1이면 세계지도.zoom

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(myCheck == false) {
                    Uri uri = Uri.parse("tel:"+tourTel[pos]);
                    Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                    startActivity(intent);
                }

                    return false;
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if(myCheck == false) {
                    Uri uri = Uri.parse(tourHomePage[pos]);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"일반 지도");
        menu.add(0,2,0,"위성 지도");
        menu.add(0,3,0,"내 위치");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);//일반지도
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);//위성지도
                break;
            case 3:
                myCheck = true;
                if(myLatLng[0] == null) {
                    //내 위치 정보 가져오는 중입니다.
                } else {
                    apiClien.connect();
                    tourMove(myLatLng);
                }

                break;

                //mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN); //내위치(GPS, 기계로 찾는 방법)
                //break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location location = providerApi.getLastLocation(apiClien);
        if(location != null) {
            myLatLng[0] = location.getLatitude();
            myLatLng[1] = location.getLongitude();
        } else {
            Toast.makeText(this, "정보를 받아올 수 없습니다.", Toast.LENGTH_SHORT).show();
            //정보를 받아올 수 없습니다.
        }
        apiClien.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
