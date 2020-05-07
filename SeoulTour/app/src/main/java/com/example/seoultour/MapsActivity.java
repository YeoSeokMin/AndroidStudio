package com.example.seoultour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.google.android.gms.common.api.GoogleApiClient.*;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        ConnectionCallbacks, OnConnectionFailedListener {

    private GoogleMap mMap;
    Spinner spSeoul;
    String seoul[]={ "국립중앙박물관","남산골 한옥마을 ", "예술의 전당 ", "청계천   ", "63빌딩 ", "남산타워 ", "경복궁", "김치문화체험관 ", "서울올림픽기념관 ", "국립민속박물관 ", "서대문형무소역사관 ", "창덕궁 "};
    Double lat[]={ 37.5240867,37.5591447, 37.4785361, 37.5696512, 37.5198158, 37.5511147, 37.5788408, 37.5629457, 37.5202976, 37.5815645, 37.5742887, 37.5826041};
    Double lng[]={126.9803881,126.9936826,127.0107423, 127.0056375, 126.9403139, 126.9878596,126.9770162, 126.9851652, 127.1159236,126.9789313,126.9562269,126.9919376};
    Double tourLatLng[]=new Double[2]; // 관광지의 위도, 경도
    Double myLatLng[] = new Double[2]; // 내 위치의 위도, 경도
    int pos;
    FusedLocationProviderApi providerApi;
    GoogleApiClient apiClient;

/*    LocationManager mylocationManager;
    LocationListener myListener;*/
    boolean myCheck; // 관광지 / 내위치 선택할수있는 메서드 EX) true(관광지) / false(내위치)
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

        int pCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (pCheck == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        } else {
            myLocationService();
        }

        //myLocationService();

        spSeoul.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myCheck=false;
                pos = position;
                tourLatLng[0]=lat[pos];
                tourLatLng[1]=lng[pos];
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
       /* pCheck = ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);

        if (pCheck == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION},100);
        } else {
            mylocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1,1000,myListener);

        }
    }*/ //GPS와 NETWORK를 활용한 내위치 찾기

    // 퍼미션 체크 후 실행하는 메서드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        myLocationService();
    }
    // 내위치 정보 알려주는 메서드
    void myLocationService() {
       /* mylocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //mylocationManager.getProvider(LocationManager.NETWORK_PROVIDER); //네크워크 기반
        //mylocationManager.getProvider(LocationManager.GPS_PROVIDER);      //GPS 기반
        mylocationManager.getBestProvider(new Criteria(),true); //알아서 네크워크 / GPS 중 빠른것으로 선택해서 처리해줌.




        myListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                myLatLng[0] = location.getLatitude(); // 위도
                myLatLng[1] = location.getLongitude(); // 경도
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                switch (status) {
                    case LocationProvider.OUT_OF_SERVICE: //서비스 지역을 벗어났을때,
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE: //일시적 불능
                        break;
                    case LocationProvider.AVAILABLE: //이용 가능할때
                        break;

                }
            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(getApplicationContext(),"현재 위치 서비스를 사용가능 합니다.",Toast.LENGTH_SHORT).show();
                // 현재 내 위치 서비스를 사용 가능
            }

            @Override
            public void onProviderDisabled(String provider) {
                Toast.makeText(getApplicationContext(),"현재 위치 서비스를 사용 할 수 없습니다.",Toast.LENGTH_SHORT).show();
                // 현재 내 위치 서비스 사용 불가능
            }
        };
    }*/
    // 처음 실행하는 지도 메서드
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    //서울 관광 지도 서비스 메서드
    public void tourMove(Double locationLatLng[]){
        String tourAddress[]={"서울특별시 용산구 서빙고로 137",
                "서울특별시 중구 퇴계로34길 28",
                "서울특별시 서초구 남부순환로 2364",
                "서울특별시 종로구 창신동","서울특별시 영등포구 63로 50",
                "서울특별시 용산구 남산공원길 105",
                "서울특별시 종로구 삼청로 37","서울특별시 중구 명동2가 32-2",
                "서울특별시 송파구 올림픽로 448",
                "서울특별시 종로구 삼청로 37","서울특별시 서대문구 통일로 251",
                "서울특별시 종로구 율곡로 99"};

        final String tourTel[] = {"02-2077-9000","02-2264-4412",
                "02-580-1300","02-2290-6114","02-789-5663",
                "02-3455-9277","02-3700-3900","02-318-7051",
                "02-410-1354","02-3704-3114","02-360-8590",
                "02-762-8261"};

        final String tourHomePage[] = {"http://www.museum.go.kr",
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

        String address[] = {};

        LatLng tourPos=new LatLng(locationLatLng[0],locationLatLng[1]);
        //mMap.addMarker(new MarkerOptions().position(tourPos).title(seoul[pos])); // 안드로이드에서 제공된 마커 사용
        MarkerOptions markerOpt = new MarkerOptions(); // 제공된 마커가 아닌 직접 만든 마커 사용
        markerOpt.position(tourPos); //마커가 찍히는 위치 tourPos = 위도와 경도
        markerOpt.icon(BitmapDescriptorFactory.fromResource(R.drawable.point2)); //마커 이미지의 위치
        if (myCheck == true){
            markerOpt.title("현재 내 위치");
            markerOpt.snippet("위도:"+locationLatLng[0]+"--경도:"+locationLatLng[1]);

        }else{
            markerOpt.title(seoul[pos]);
            markerOpt.snippet(tourAddress[pos] + "[전화번호 : " +tourTel[pos]+"]");
        }

        mMap.addMarker(markerOpt).showInfoWindow(); // 내가만든 마커를 추가하고. 화면에 표시
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tourPos,15));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (myCheck==false){
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
                if (myCheck==false){
                    Uri uri = Uri.parse(tourHomePage[pos]);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }

            }
        });
    }
    //옵션 메서드
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"일반 지도");
        menu.add(0,2,0,"위성 지도");
        menu.add(0,3,0,"내 위치");
        return true;
    }
    //옵션선택시 항목선택 메서드
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
                if (myLatLng[0]==null){
                    Toast.makeText(getApplicationContext(),"내 위치 정보를 가져오는 중입니다.",Toast.LENGTH_SHORT).show();
                }else{
                    tourMove(myLatLng);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
