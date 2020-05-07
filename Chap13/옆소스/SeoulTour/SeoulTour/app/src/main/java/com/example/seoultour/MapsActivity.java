package com.example.seoultour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Spinner spSeoul;
    String seoul[]={ "국립중앙박물관","남산골 한옥마을 ", "예술의 전당 ", "청계천   ", "63빌딩 ", "남산타워 ", "경복궁", "김치문화체험관 ", "서울올림픽기념관 ", "국립민속박물관 ", "서대문형무소역사관 ", "창덕궁 "};
    Double lat[]={ 37.5240867,37.5591447, 37.4785361, 37.5696512, 37.5198158, 37.5511147, 37.5788408, 37.5629457, 37.5202976, 37.5815645, 37.5742887, 37.5826041};
    Double lng[]={126.9803881,126.9936826,127.0107423, 127.0056375, 126.9403139, 126.9878596,126.9770162, 126.9851652, 127.1159236,126.9789313,126.9562269,126.9919376};
    Double tourLatLng[]=new Double[2];
    int pos;

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
        spSeoul.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                tourLatLng[0]=lat[pos];
                tourLatLng[1]=lng[pos];
                tourMove(tourLatLng);
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public void tourMove(Double locationLatLng[]){
        LatLng tourPos=new LatLng(locationLatLng[0],locationLatLng[1]);
        mMap.addMarker(new MarkerOptions().position(tourPos).title(seoul[pos]));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tourPos,15));
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
                mMap.setMapType(GoogleMap);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
