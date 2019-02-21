package com.libill.demos.activity;

import android.Manifest;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import androidx.annotation.NonNull;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class SimpleCursorAdapterActivity extends Activity {
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new ListView(this);
        setContentView(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SimpleCursorAdapterActivityPermissionsDispatcher.requestPhoneWithPermissionCheck(SimpleCursorAdapterActivity.this);
    }

    private void setData() {
        //获得一个指向系统通讯录数据库的Cursor对象获得数据来源
        Cursor cur = getContentResolver().query(People.CONTENT_URI, null, null, null, null);
        startManagingCursor(cur);
        //实例化列表适配器
        ListAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cur, new String[]{People.NAME}, new int[]{android.R.id.text1});
        listView.setAdapter(adapter);
    }

    @NeedsPermission({Manifest.permission.READ_CONTACTS})
    public void requestPhone() {
        setData();
    }

    @OnPermissionDenied({Manifest.permission.READ_CONTACTS})
    public void requestPhoneDenied() {
        Toast.makeText(this, "已禁止该权限", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain({Manifest.permission.READ_CONTACTS})
    public void requestPhoneNeverAskAgain() {
        Toast.makeText(this, "请到设置开启权限", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SimpleCursorAdapterActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
