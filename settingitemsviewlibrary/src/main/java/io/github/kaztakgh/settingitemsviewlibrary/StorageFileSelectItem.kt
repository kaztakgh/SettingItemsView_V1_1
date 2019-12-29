/**
 * @file StorageFileSelectItem.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * ファイルを指定するアイテムの作成<br>
 *     Android6.0以降で使用する場合、Permissionの確認が必須になる<br>
 *         それ以前の場合はManifestに使用することを記述する
 *
 * @property title タイトル(省略不可)
 * @property tag タグ(識別子、省略不可)
 * @property bmpIcon 左側に表示するアイコン(省略時null)
 * @property enabled 選択可能であるか(省略時true)
 * @property uri ファイルパス
 * @property mimeType ファイル形式(省略時どの形式も選択可能)
 * @property requestCode リクエストコード(省略時1)
 * @property displayFullPath フルパスで表示するか(true:表示する、false:表示しない、省略時表示しない)
 */
data class StorageFileSelectItem(
    override val title: String,
    override val tag: String,
    val bmpIcon: Bitmap? = null,
    var enabled: Boolean = true,
    var uri: Uri? = null,
    val mimeType: String = "*/*",
    val requestCode: Int = 1,
    val displayFullPath: Boolean = false
) : ItemInterface , Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.readParcelable(Uri::class.java.classLoader),
        parcel.readString() as String,
        parcel.readInt(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(tag)
        parcel.writeParcelable(bmpIcon, flags)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeParcelable(uri, flags)
        parcel.writeString(mimeType)
        parcel.writeInt(requestCode)
        parcel.writeByte(if (displayFullPath) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StorageFileSelectItem> {
        override fun createFromParcel(parcel: Parcel): StorageFileSelectItem {
            return StorageFileSelectItem(parcel)
        }

        override fun newArray(size: Int): Array<StorageFileSelectItem?> {
            return arrayOfNulls(size)
        }
    }

    /**
     * 外部ストレージの入出力のパーミッションを確認する
     *
     * @param context データ
     */
    internal fun checkExternalStoragePermission(context: Context) {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> requestPermission(context)
            else -> openDocument(context)
        }
    }

    /**
     * パーミッションによる処理の分岐
     * SDKがMarshmallow以降の場合のみ処理を行う
     *
     * @param context
     */
    private fun requestPermission(context: Context) {
        // SDKがMarshmallow以前の場合は処理しない
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return
        // 既に許可を得ている場合、ファイル選択のウィンドウを開く
        if (checkPermission(context))
            openDocument(context)
        else {
            // ダイアログ出力許可がある場合
            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, WRITE_EXTERNAL_STORAGE)) {
                // 許可を求めるパーミッションダイアログを出力する
                ActivityCompat.requestPermissions(
                    context,
                    arrayOf(WRITE_EXTERNAL_STORAGE),
                    requestCode
                )
            }
            // ダイアログ出力許可がない場合
            else
            // 操作できないという警告文を出力する
                Toast.makeText(context, "ファイルを読み込むためのパーミッションが必要です", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * WRITE_EXTERNAL_STORAGEのパーミッション確認
     *
     * @param context データ
     * @return boolean WRITE_EXTERNAL_STORAGEのパーミッションの戻り値がPERMISSION_GRANTEDであるか
     */
    @TargetApi(23)
    private fun checkPermission(context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * SDKがLollipop以前の場合にファイル選択の画面を出力する処理を行う
     * Marshmallow以降でも、WRITE_EXTERNAL_STORAGEのパーミッションを得た場合のファイル選択にはこの処理が必要
     *
     * @param context データ
     */
    private fun openDocument(context: Context) {
        // Intentの生成
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        // uriが設定されている場合
        if (uri != null) {
            // uriとMIMEタイプの設定
            intent.setDataAndType(uri, mimeType)
        }
        else {
            // MIMEタイプの設定
            intent.type = mimeType
        }
        // ActivityもしくはFragmentで処理してもらう
        (context as Activity).startActivityForResult(intent, requestCode)
    }

    /**
     * Uriからファイルパスを取得する
     *
     * @param context データ
     * @param uri URI
     * @return ファイルパス
     */
    internal fun getContentPathStringFromUri(context: Context, uri: Uri): String {
        // 変数初期化
        var path = ""
        val cr = context.contentResolver
        // この方法のみで対応可能ではない模様(Androidデフォルトのファイルを使用していない場合など)
        // 選択時に使用するアプリによって方法が変わるのも含めて対処するべき？
        var projection: Array<String>? = null
        var id: String? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            id = DocumentsContract.getDocumentId(uri)
        }
        val mimeTypeResult = cr.getType(uri)
        val selection = "_id=?"
        var selectionArgs = arrayOfNulls<String>(0)
        if (id != null) {
            selectionArgs =
                arrayOf(id.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1])
        }
        if (mimeTypeResult != null) {
            projection = when {
                mimeTypeResult.startsWith("audio") -> arrayOf(MediaStore.Audio.Media.DATA)
                mimeTypeResult.startsWith("image") -> arrayOf(MediaStore.Images.Media.DATA)
                mimeTypeResult.startsWith("video") -> arrayOf(MediaStore.Video.Media.DATA)
                else -> arrayOf(MediaStore.MediaColumns.DATA)
            }
        }
        val crsCursor = cr.query(
            MediaStore.Files.getContentUri("external"),
            projection,
            selection,
            selectionArgs, null
        )
        if (crsCursor != null) {
            crsCursor.moveToFirst()
            path = crsCursor.getString(0)
            crsCursor.close()
        }

        // 文字列の整形
        if (!displayFullPath) {
            val pathArray = path.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            path = pathArray[pathArray.size - 1]
        }
        return path
    }
}