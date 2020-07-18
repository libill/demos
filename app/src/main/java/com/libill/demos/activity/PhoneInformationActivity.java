package com.libill.demos.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.widget.Toast;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class PhoneInformationActivity extends Activity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView = new TextView(this);
        textView.setMovementMethod(new ScrollingMovementMethod());
        setContentView(textView);

        PhoneInformationActivityPermissionsDispatcher.requestPhoneWithPermissionCheck(PhoneInformationActivity.this);
    }

    @NeedsPermission({Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION})
    public void requestPhone() {
        textView.setText(getPhoneInfo());
    }

    @OnPermissionDenied({Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION})
    public void requestPhoneDenied() {
        textView.setText("获取权限失败无法显示内容");
        Toast.makeText(this, "已禁止该权限", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain({Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION})
    public void requestPhoneNeverAskAgain() {
        textView.setText("获取权限失败无法显示内容");
        Toast.makeText(this, "请到设置开启权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PhoneInformationActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public String getPhoneInfo() {
        //BOARD 主板
        String phoneInfo = "";
        phoneInfo += "主板" + "\n";
        phoneInfo += "BOARD: " + android.os.Build.BOARD + "\n";
        phoneInfo += "BOOTLOADER: " + android.os.Build.BOOTLOADER + "\n";
        //BRAND 运营商
        phoneInfo += "运营商" + "\n";
        phoneInfo += "BRAND: " + android.os.Build.BRAND + "\n";
        phoneInfo += "CPU_ABI: " + android.os.Build.CPU_ABI + "\n";
        phoneInfo += "CPU_ABI2: " + android.os.Build.CPU_ABI2 + "\n";
        //DEVICE 驱动
        phoneInfo += "驱动" + "\n";
        phoneInfo += "DEVICE: " + android.os.Build.DEVICE + "\n";
        //DISPLAY 显示
        phoneInfo += "显示" + "\n";
        phoneInfo += "DISPLAY: " + android.os.Build.DISPLAY + "\n";
        //指纹
        phoneInfo += "指纹" + "\n";
        phoneInfo += "FINGERPRINT: " + android.os.Build.FINGERPRINT + "\n";
        //HARDWARE 硬件
        phoneInfo += "硬件" + "\n";
        phoneInfo += "HARDWARE: " + android.os.Build.HARDWARE + "\n";
        phoneInfo += "HOST: " + android.os.Build.HOST + "\n";
        phoneInfo += "ID: " + android.os.Build.ID + "\n";
        //MANUFACTURER 生产厂家
        phoneInfo += "生产厂家" + "\n";
        phoneInfo += "MANUFACTURER: " + android.os.Build.MANUFACTURER + "\n";
        //MODEL 机型
        phoneInfo += "机型" + "\n";
        phoneInfo += "MODEL: " + android.os.Build.MODEL + "\n";
        phoneInfo += "PRODUCT: " + android.os.Build.PRODUCT + "\n";
        phoneInfo += "RADIO: " + android.os.Build.RADIO + "\n";
        phoneInfo += "RADITAGSO: " + android.os.Build.TAGS + "\n";
        phoneInfo += "TIME: " + android.os.Build.TIME + "\n";
        phoneInfo += "TYPE: " + android.os.Build.TYPE + "\n";
        phoneInfo += "USER: " + android.os.Build.USER + "\n";
        //VERSION.RELEASE 固件版本
        phoneInfo += "固件版本" + "\n";
        phoneInfo += "VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE + "\n";
        phoneInfo += "VERSION.CODENAME: " + android.os.Build.VERSION.CODENAME + "\n";
        //VERSION.INCREMENTAL 基带版本
        phoneInfo += "基带版本" + "\n";
        phoneInfo += "VERSION.INCREMENTAL: " + android.os.Build.VERSION.INCREMENTAL + "\n";
        //VERSION.SDK SDK版本
        phoneInfo += "SDK版本" + "\n";
        phoneInfo += "VERSION.SDK: " + android.os.Build.VERSION.SDK + "\n";
        phoneInfo += "VERSION.SDK_INT: " + android.os.Build.VERSION.SDK_INT + "\n";

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);

        phoneInfo += "屏幕分辨率:" + metrics.widthPixels + "*" + metrics.heightPixels
//                + "\nDeviceId，唯一的设备ID:" + tm.getDeviceId()
                + "\nthe phone number:" + tm.getLine1Number()
//                + "\nsoftware version:" + tm.getDeviceSoftwareVersion()
                + "\nService Provider Name (SPN):" + tm.getSimOperatorName()
                + "\ndevice phone type:" + tm.getPhoneType()
                + "\nNetworkType:" + tm.getNetworkType()
                + "\ngetNetworkCountryIso:" + tm.getNetworkCountryIso()
                + "\ngetSimState:" + tm.getSimState();
        /*
         * 电话状态：
         * 1.tm.CALL_STATE_IDLE=0          无活动
         * 2.tm.CALL_STATE_RINGING=1  响铃
         * 3.tm.CALL_STATE_OFFHOOK=2  摘机
         */
        tm.getCallState();//int     

        /*
         * 电话方位：
         *
         */
        tm.getCellLocation();//CellLocation     

        /*
         * 唯一的设备ID：
         * GSM手机的 IMEI 和 CDMA手机的 MEID.
         * Return null if device ID is not available.
         */
        tm.getDeviceId();//String

        /*
         * 设备的软件版本号：
         * 例如：the IMEI/SV(software version) for GSM phones.
         * Return null if the software version is not available.
         */
        tm.getDeviceSoftwareVersion();//String     

        /*
         * 手机号：
         * GSM手机的 MSISDN.
         * Return null if it is unavailable.
         */
        tm.getLine1Number();//String     

        /*
         * 附近的电话的信息:
         * 类型：List<NeighboringCellInfo>
         * 需要权限：android.Manifest.permission#ACCESS_COARSE_UPDATES
         */
        //tm.getNeighboringCellInfo();//List<NeighboringCellInfo>

        /*
         * 获取ISO标准的国家码，即国际长途区号。
         * 注意：仅当用户已在网络注册后有效。
         *       在CDMA网络中结果也许不可靠。
         */
        tm.getNetworkCountryIso();//String     

        /*
         * MCC+MNC(mobile country code + mobile network code)
         * 注意：仅当用户已在网络注册时有效。
         *    在CDMA网络中结果也许不可靠。
         */
        tm.getNetworkOperator();//String     

        /*
         * 按照字母次序的current registered operator(当前已注册的用户)的名字
         * 注意：仅当用户已在网络注册时有效。
         *    在CDMA网络中结果也许不可靠。
         */
        tm.getNetworkOperatorName();//String     
             
        /*   
         * 当前使用的网络类型：   
         * 例如： NETWORK_TYPE_UNKNOWN  网络类型未知  0   
           NETWORK_TYPE_GPRS     GPRS网络  1   
           NETWORK_TYPE_EDGE     EDGE网络  2   
           NETWORK_TYPE_UMTS     UMTS网络  3   
           NETWORK_TYPE_HSDPA    HSDPA网络  8    
           NETWORK_TYPE_HSUPA    HSUPA网络  9   
           NETWORK_TYPE_HSPA     HSPA网络  10   
           NETWORK_TYPE_CDMA     CDMA网络,IS95A 或 IS95B.  4   
           NETWORK_TYPE_EVDO_0   EVDO网络, revision 0.  5   
           NETWORK_TYPE_EVDO_A   EVDO网络, revision A.  6   
           NETWORK_TYPE_1xRTT    1xRTT网络  7   
         */
        tm.getNetworkType();//int     
             
        /*   
         * 手机类型：   
         * 例如： PHONE_TYPE_NONE  无信号   
           PHONE_TYPE_GSM   GSM信号   
           PHONE_TYPE_CDMA  CDMA信号   
         */
        tm.getPhoneType();//int     

        /*
         * Returns the ISO country code equivalent for the SIM provider's country code.
         * 获取ISO国家码，相当于提供SIM卡的国家码。
         *
         */
        tm.getSimCountryIso();//String     

        /*
         * Returns the MCC+MNC (mobile country code + mobile network code) of the provider of the SIM. 5 or 6 decimal digits.
         * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字.
         * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
         */
        tm.getSimOperator();//String     

        /*
         * 服务商名称：
         * 例如：中国移动、联通
         * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
         */
        tm.getSimOperatorName();//String     

        /*
         * SIM卡的序列号：
         * 需要权限：READ_PHONE_STATE
         */
        tm.getSimSerialNumber();//String     
             
        /*   
         * SIM的状态信息：   
         *  SIM_STATE_UNKNOWN          未知状态 0   
         SIM_STATE_ABSENT           没插卡 1   
         SIM_STATE_PIN_REQUIRED     锁定状态，需要用户的PIN码解锁 2   
         SIM_STATE_PUK_REQUIRED     锁定状态，需要用户的PUK码解锁 3   
         SIM_STATE_NETWORK_LOCKED   锁定状态，需要网络的PIN码解锁 4   
         SIM_STATE_READY            就绪状态 5   
         */
        tm.getSimState();//int     

        /*
         * 唯一的用户ID：
         * 例如：IMSI(国际移动用户识别码) for a GSM phone.
         * 需要权限：READ_PHONE_STATE
         */
        tm.getSubscriberId();//String     

        /*
         * 取得和语音邮件相关的标签，即为识别符
         * 需要权限：READ_PHONE_STATE
         */
        tm.getVoiceMailAlphaTag();//String     

        /*
         * 获取语音邮件号码：
         * 需要权限：READ_PHONE_STATE
         */
        tm.getVoiceMailNumber();//String     

        /*
         * ICC卡是否存在
         */
        tm.hasIccCard();//boolean     

        /*
         * 是否漫游:
         * (在GSM用途下)
         */
        tm.isNetworkRoaming();//

        return phoneInfo;
    }
}
