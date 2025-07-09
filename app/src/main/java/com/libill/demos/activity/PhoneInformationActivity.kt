package com.libill.demos.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.CalendarContract
import android.telephony.TelephonyManager
import android.text.method.ScrollingMovementMethod
import android.util.DisplayMetrics
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.libill.demos.base.BaseActivity
import com.libill.demos.util.getReadPhoneStatePermission
import kotlinx.coroutines.launch

class PhoneInformationActivity : BaseActivity() {
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        textView = TextView(this)
        textView?.setTextColor(Color.BLACK)
        textView?.movementMethod = ScrollingMovementMethod()
        setContentView(textView)
        lifecycleScope.launch {
            val granted = getReadPhoneStatePermission()
            if (granted) {
                requestPhone()
            } else {
                requestPhoneDenied()
            }
        }
    }

    fun requestPhone() {
        textView!!.text = phoneInfo
    }

   fun requestPhoneDenied() {
        textView!!.text = "获取权限失败无法显示内容"
        Toast.makeText(this, "已禁止该权限", Toast.LENGTH_SHORT).show()
    }

    val phoneInfo: String
        get() {
            //BOARD 主板
            var phoneInfo = ""
            phoneInfo += "主板" + "\n"
            phoneInfo += "BOARD: " + Build.BOARD + "\n"
            phoneInfo += "BOOTLOADER: " + Build.BOOTLOADER + "\n"
            //BRAND 运营商
            phoneInfo += "运营商" + "\n"
            phoneInfo += "BRAND: " + Build.BRAND + "\n"
            phoneInfo += "CPU_ABI: " + Build.CPU_ABI + "\n"
            phoneInfo += "CPU_ABI2: " + Build.CPU_ABI2 + "\n"
            //DEVICE 驱动
            phoneInfo += "驱动" + "\n"
            phoneInfo += "DEVICE: " + Build.DEVICE + "\n"
            //DISPLAY 显示
            phoneInfo += "显示" + "\n"
            phoneInfo += "DISPLAY: " + Build.DISPLAY + "\n"
            //指纹
            phoneInfo += "指纹" + "\n"
            phoneInfo += "FINGERPRINT: " + Build.FINGERPRINT + "\n"
            //HARDWARE 硬件
            phoneInfo += "硬件" + "\n"
            phoneInfo += "HARDWARE: " + Build.HARDWARE + "\n"
            phoneInfo += "HOST: " + Build.HOST + "\n"
            phoneInfo += "ID: " + Build.ID + "\n"
            //MANUFACTURER 生产厂家
            phoneInfo += "生产厂家" + "\n"
            phoneInfo += "MANUFACTURER: " + Build.MANUFACTURER + "\n"
            //MODEL 机型
            phoneInfo += "机型" + "\n"
            phoneInfo += "MODEL: " + Build.MODEL + "\n"
            phoneInfo += "PRODUCT: " + Build.PRODUCT + "\n"
            phoneInfo += "RADIO: " + Build.RADIO + "\n"
            phoneInfo += "RADITAGSO: " + Build.TAGS + "\n"
            phoneInfo += "TIME: " + Build.TIME + "\n"
            phoneInfo += "TYPE: " + Build.TYPE + "\n"
            phoneInfo += "USER: " + Build.USER + "\n"
            //VERSION.RELEASE 固件版本
            phoneInfo += "固件版本" + "\n"
            phoneInfo += "VERSION.RELEASE: " + Build.VERSION.RELEASE + "\n"
            phoneInfo += "VERSION.CODENAME: " + Build.VERSION.CODENAME + "\n"
            //VERSION.INCREMENTAL 基带版本
            phoneInfo += "基带版本" + "\n"
            phoneInfo += "VERSION.INCREMENTAL: " + Build.VERSION.INCREMENTAL + "\n"
            //VERSION.SDK SDK版本
            phoneInfo += "SDK版本" + "\n"
            phoneInfo += "VERSION.SDK: " + Build.VERSION.SDK + "\n"
            phoneInfo += "VERSION.SDK_INT: " + Build.VERSION.SDK_INT + "\n"

            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            val tm =
                getSystemService(TELEPHONY_SERVICE) as TelephonyManager

            phoneInfo += ("""
     屏幕分辨率:${metrics.widthPixels}*${metrics.heightPixels}
     the phone number:${tm.line1Number}
     Service Provider Name (SPN):${tm.simOperatorName}
     device phone type:${tm.phoneType}
     NetworkType:${tm.networkType}
     getNetworkCountryIso:${tm.networkCountryIso}
     getSimState:${tm.simState}
     """.trimIndent())
            /*
     * 电话状态：
     * 1.tm.CALL_STATE_IDLE=0          无活动
     * 2.tm.CALL_STATE_RINGING=1  响铃
     * 3.tm.CALL_STATE_OFFHOOK=2  摘机
     */
            tm.callState //int     

            /*
     * 电话方位：
     *
     */
            tm.cellLocation //CellLocation     

            /*
     * 唯一的设备ID：
     * GSM手机的 IMEI 和 CDMA手机的 MEID.
     * Return null if device ID is not available.
     */
//            tm.deviceId //String

            /*
     * 设备的软件版本号：
     * 例如：the IMEI/SV(software version) for GSM phones.
     * Return null if the software version is not available.
     */
            tm.deviceSoftwareVersion //String     

            /*
     * 手机号：
     * GSM手机的 MSISDN.
     * Return null if it is unavailable.
     */
            tm.line1Number //String     

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
            tm.networkCountryIso //String     

            /*
     * MCC+MNC(mobile country code + mobile network code)
     * 注意：仅当用户已在网络注册时有效。
     *    在CDMA网络中结果也许不可靠。
     */
            tm.networkOperator //String     

            /*
     * 按照字母次序的current registered operator(当前已注册的用户)的名字
     * 注意：仅当用户已在网络注册时有效。
     *    在CDMA网络中结果也许不可靠。
     */
            tm.networkOperatorName //String     


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
            tm.networkType //int     


            /*   
             * 手机类型：   
             * 例如： PHONE_TYPE_NONE  无信号   
               PHONE_TYPE_GSM   GSM信号   
               PHONE_TYPE_CDMA  CDMA信号   
             */
            tm.phoneType //int     

            /*
     * Returns the ISO country code equivalent for the SIM provider's country code.
     * 获取ISO国家码，相当于提供SIM卡的国家码。
     *
     */
            tm.simCountryIso //String     

            /*
     * Returns the MCC+MNC (mobile country code + mobile network code) of the provider of the SIM. 5 or 6 decimal digits.
     * 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字.
     * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
     */
            tm.simOperator //String     

            /*
     * 服务商名称：
     * 例如：中国移动、联通
     * SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断).
     */
            tm.simOperatorName //String     

            /*
     * SIM卡的序列号：
     * 需要权限：READ_PHONE_STATE
     */
//            tm.simSerialNumber //String


            /*   
             * SIM的状态信息：   
             *  SIM_STATE_UNKNOWN          未知状态 0   
             SIM_STATE_ABSENT           没插卡 1   
             SIM_STATE_PIN_REQUIRED     锁定状态，需要用户的PIN码解锁 2   
             SIM_STATE_PUK_REQUIRED     锁定状态，需要用户的PUK码解锁 3   
             SIM_STATE_NETWORK_LOCKED   锁定状态，需要网络的PIN码解锁 4   
             SIM_STATE_READY            就绪状态 5   
             */
            tm.simState //int     

            /*
     * 唯一的用户ID：
     * 例如：IMSI(国际移动用户识别码) for a GSM phone.
     * 需要权限：READ_PHONE_STATE
     */
//            tm.subscriberId //String

            /*
     * 取得和语音邮件相关的标签，即为识别符
     * 需要权限：READ_PHONE_STATE
     */
            tm.voiceMailAlphaTag //String     

            /*
     * 获取语音邮件号码：
     * 需要权限：READ_PHONE_STATE
     */
            tm.voiceMailNumber //String     

            /*
     * ICC卡是否存在
     */
            tm.hasIccCard() //boolean     

            /*
     * 是否漫游:
     * (在GSM用途下)
     */
            tm.isNetworkRoaming //

            return phoneInfo
        }
}
