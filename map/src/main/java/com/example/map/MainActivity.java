package com.example.map;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.Iterator;
import java.util.List;
import java.util.logging.LoggingPermission;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private MyLocationConfiguration.LocationMode mLocationMode;
    private boolean isFirstLoc = true;//记录是否为第一次定位

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();

        Log.i("test","权限检查开始");
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        //权限检查
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            Log.i("test","权限检查未通过");
            return;
        }
        Log.i("test","权限检查通过");
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,//指定GP定位的提供者
                100,//间隔时间
                1,//间隔距离
                new LocationListener() {//监听定位是否改变
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                         //信息发生改变回调
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                        //状态改变回调
                    }

                    @Override
                    public void onProviderEnabled(@NonNull String provider) {
                        //启动时触发
                    }

                    @Override
                    public void onProviderDisabled(@NonNull String provider) {
                        //关闭时触发
                    }
                }
        );
       Location location =  locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//获取最新定位信息
         locationUpdate(location);//将最新定位信息保存




//        List<String> providerNames = locationManager.getAllProviders();//获取所有可用的定位
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Iterator<String> iterator = providerNames.iterator();iterator.hasNext();){
//            stringBuilder.append(iterator.next()+"\n");
//        }
//        textView.setText(stringBuilder.toString());

//        //获取基于GPS的locationProvider
//        LocationProvider locationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
//        textView.setText(locationProvider.getName());

//        //获取最佳的
//        Criteria criteria = new Criteria();
//        criteria.setCostAllowed(false);//不收费的
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);//精度最高的
//        criteria.setPowerRequirement(Criteria.POWER_LOW);//耗电量低
//        String provider = locationManager.getBestProvider(criteria,true);//获取最佳的
//        textView.setText(provider);

    }

    public  void locationUpdate(Location location){
        if (location != null){
            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
            Log.i("test","纬度:"+location.getLatitude()+"精度:"+location.getLongitude());
            if(isFirstLoc){
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(latLng);//更新坐标
                mBaiduMap.animateMapStatus(u);
                isFirstLoc = false;
            }

            MyLocationData locationData = new MyLocationData.Builder().accuracy(location.getAccuracy())
                    .direction(100)
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(locationData);
            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon);
            mLocationMode = MyLocationConfiguration.LocationMode.NORMAL;
            MyLocationConfiguration configuration = new MyLocationConfiguration(mLocationMode,true,bitmapDescriptor);
            mBaiduMap.setMyLocationConfigeration(configuration);
        }else {
            Log.i("test","没有获取到GPS");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        mMapView = null;
    }
}
