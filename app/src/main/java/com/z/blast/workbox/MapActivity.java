package com.z.blast.workbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.List;
import java.util.Map;
/**
 * Created by Random on 16.6.12.
 */
public class MapActivity extends Activity {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    public BDNotifyListener mNotifyer;
    public Vibrator mVibrator;
    MapView myMapView = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.map_main);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        myMapView = (MapView) findViewById(R.id.bmapView);
        BaiduMap mBaiduMap = myMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        this.initLocation();
        mLocationClient.start();
    }
    protected void onDestroy() {
        super.onDestroy();
        myMapView.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener);
        mLocationClient.removeNotifyEvent(mNotifyer);
        mLocationClient.stop();
    }
    protected void onResume() {
        super.onResume();
        myMapView.onResume();
    }
    protected void onPause() {
        super.onPause();
        myMapView.onPause();
    }
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//Battery_Saving,Device_Sensors
        option.setCoorType("bd09ll");
        //国测局经纬度坐标系(gcj02)，百度墨卡托坐标系(bd09)，百度经纬度坐标系(bd09ll)
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setNeedDeviceDirect(true);
        mLocationClient.setLocOption(option);
    }
    public class MyLocationListener implements BDLocationListener {
        public void onReceiveLocation(BDLocation location) {
            StringBuffer sb = new StringBuffer(256);
            sb.append("latitude:"+location.getLatitude());
            sb.append("\n"+"longitude:"+location.getLongitude());
            sb.append("\n"+"radius:"+location.getRadius());
            sb.append("\n"+"address:"+location.getAddrStr());
            Log.i("BaiduLocationApiDem", sb.toString());
            LatLng cenpt = new LatLng(location.getLatitude(),location.getLongitude());
            MapStatus myMapStatus = new MapStatus.Builder().target(cenpt).zoom(17).build();//zoom表示缩放级别
            MapStatusUpdate myMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(myMapStatus);
            myMapView.getMap().setMapStatus(myMapStatusUpdate);
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.flag_24);
            OverlayOptions option = new MarkerOptions().position(cenpt).icon(bitmap);
            myMapView.getMap().clear();
            myMapView.getMap().addOverlay(option);
            Toast.makeText(MapActivity.this, location.getAddrStr(), Toast.LENGTH_LONG).show();
            /*AlertDialog.Builder builder=new AlertDialog.Builder(MapActivity.this)
                    .setTitle("Location")
                    .setIcon(R.drawable.flag_24)
                    .setMessage(location.getAddrStr());
            builder.create().show();*/
            mLocationClient.stop();
        }
    }
}
