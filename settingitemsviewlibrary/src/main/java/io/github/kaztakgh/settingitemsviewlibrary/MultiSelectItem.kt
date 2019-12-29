/**
 * @file MultiSelectItem.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import io.github.kaztakgh.dialogfragmentslibrary.MultiSelectorDialog
import io.github.kaztakgh.dialogfragmentslibrary.SelectorItemData

/**
 * 指定されたものから複数選択するアイテムの作成<br>
 *     SingleSelectItemと同様に、選択するものに画像、音楽などを追加することができる<br>
 *         アイテムを押下したとき、ダイアログを表示する
 *
 * @property title タイトル(省略不可)
 * @property tag タグ(識別子、省略不可)
 * @property bmpIcon 左側に表示するアイコン(省略時null)
 * @property enabled 選択可能であるか(省略時true)
 * @property options 選択肢一覧(この項目と選択肢画像のどちらかは必須)
 * @property optionBitmaps 選択肢画像
 * @property optionSounds 効果音(省略時null)
 * @property optionSelectable 選択肢が選べるか(省略時全てtrue)
 * @property columns ダイアログの表示列(省略時1)
 * @property bitmapWidth (画像がある場合)画像の幅
 * @property bitmapHeight(画像がある場合)画像の高さ
 * @property select 初期選択位置(省略時全てfalse)
 * @property nothingsSelectString なにも選択されていないときのテキスト
 * @property requestCode リクエストコード
 */
data class MultiSelectItem(
    override val title: String,
    override val tag: String,
    val bmpIcon: Bitmap? = null,
    var enabled: Boolean = true,
    val options: Array<String>? = null,
    val optionBitmaps: Array<Bitmap>? = null,
    val optionSounds: IntArray? = null,
    val optionSelectable: BooleanArray? = null,
    val columns: Int = 1,
    val bitmapWidth: Int = io.github.kaztakgh.dialogfragmentslibrary.SelectorItemAdapter.INIT_GRAPH_MAX_WIDTH_DP,
    val bitmapHeight: Int = io.github.kaztakgh.dialogfragmentslibrary.SelectorItemAdapter.INIT_GRAPH_MAX_HEIGHT_DP,
    var select: BooleanArray? = null,
    val nothingsSelectString: String = "",
    val requestCode: Int = 1
) : ItemInterface, Parcelable {
    // 文字か画像は必須にする
    init {
        require(!(options == null && optionBitmaps == null))
    }

    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readParcelable(Bitmap::class.java.classLoader),
        parcel.readByte() != 0.toByte(),
        parcel.createStringArray(),
        parcel.createTypedArray(Bitmap.CREATOR),
        parcel.createIntArray(),
        parcel.createBooleanArray(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createBooleanArray() as BooleanArray,
        parcel.readString() as String,
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(tag)
        parcel.writeParcelable(bmpIcon, flags)
        parcel.writeByte(if (enabled) 1 else 0)
        parcel.writeStringArray(options)
        parcel.writeTypedArray(optionBitmaps, flags)
        parcel.writeIntArray(optionSounds)
        parcel.writeBooleanArray(optionSelectable)
        parcel.writeInt(columns)
        parcel.writeInt(bitmapWidth)
        parcel.writeInt(bitmapHeight)
        parcel.writeBooleanArray(select)
        parcel.writeString(nothingsSelectString)
        parcel.writeInt(requestCode)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MultiSelectItem) return false

        if (title != other.title) return false
        if (tag != other.tag) return false
        if (bmpIcon != other.bmpIcon) return false
        if (enabled != other.enabled) return false
        if (options != null) {
            if (other.options == null) return false
            if (!options.contentEquals(other.options)) return false
        } else if (other.options != null) return false
        if (optionBitmaps != null) {
            if (other.optionBitmaps == null) return false
            if (!optionBitmaps.contentEquals(other.optionBitmaps)) return false
        } else if (other.optionBitmaps != null) return false
        if (optionSounds != null) {
            if (other.optionSounds == null) return false
            if (!optionSounds.contentEquals(other.optionSounds)) return false
        } else if (other.optionSounds != null) return false
        if (optionSelectable != null) {
            if (other.optionSelectable == null) return false
            if (!optionSelectable.contentEquals(other.optionSelectable)) return false
        } else if (other.optionSelectable != null) return false
        if (columns != other.columns) return false
        if (bitmapWidth != other.bitmapWidth) return false
        if (bitmapHeight != other.bitmapHeight) return false
        if (select != null) {
            if (other.select == null) return false
        } else if (other.select != null) return false
        if (nothingsSelectString != other.nothingsSelectString) return false
        if (requestCode != other.requestCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + tag.hashCode()
        result = 31 * result + (bmpIcon?.hashCode() ?: 0)
        result = 31 * result + enabled.hashCode()
        result = 31 * result + (options?.contentHashCode() ?: 0)
        result = 31 * result + (optionBitmaps?.contentHashCode() ?: 0)
        result = 31 * result + (optionSounds?.contentHashCode() ?: 0)
        result = 31 * result + (optionSelectable?.contentHashCode() ?: 0)
        result = 31 * result + columns
        result = 31 * result + bitmapWidth
        result = 31 * result + bitmapHeight
        result = 31 * result + (select?.contentHashCode() ?: 0)
        result = 31 * result + requestCode
        return result
    }

    companion object CREATOR : Parcelable.Creator<MultiSelectItem> {
        override fun createFromParcel(parcel: Parcel): MultiSelectItem {
            return MultiSelectItem(parcel)
        }

        override fun newArray(size: Int): Array<MultiSelectItem?> {
            return arrayOfNulls(size)
        }
    }

    /**
     * 選択したアイテムの文字列出力
     */
    internal val text: String
        get() {
            var text = ""
            if (select == null) {
                return if (nothingsSelectString.isBlank()) text else nothingsSelectString
            }
            // 選択肢のテキストがある場合のみ文字列の追加を行う
            if (!options.isNullOrEmpty()) {
                select!!.forEachIndexed { index, check ->
                    // 選択されているアイテムの場合
                    if (check) {
                        // 何か選ばれている場合、カンマを追加
                        if (!text.isBlank()) text += ", "
                        text += options[index]
                    }
                }
            }
            return text
        }

    /**
     * ダイアログの構築
     *
     * @return 複数選択ダイアログ
     */
    internal fun buildDialog() : MultiSelectorDialog {
        val arraySize: Int = options?.size ?: optionBitmaps?.size!!
        val soundArray: IntArray = optionSounds ?: IntArray(arraySize){ SelectorItemData.SOUND_NONE_ID }
        val selectableArray: BooleanArray = optionSelectable ?: BooleanArray(arraySize){ true }
        val checkArray: BooleanArray = select ?: BooleanArray(arraySize){ false }

        // ダイアログの生成をoptionsとoptionBitmapsの状態によって変更する
        lateinit var dialog: MultiSelectorDialog
        // 文字と画像が両方ある場合
        if (!options.isNullOrEmpty() && !optionBitmaps.isNullOrEmpty()) {
            dialog = MultiSelectorDialog.Builder()
                .title(title)
                .options(options)
                .bitmaps(optionBitmaps)
                .sounds(soundArray)
                .selectable(selectableArray)
                .columns(columns)
                .graphWidth(bitmapWidth)
                .graphHeight(bitmapHeight)
                .positiveLabel("OK")
                .negativeLabel("Cancel")
                .initSelect(checkArray)
                .requestCode(requestCode)
                .build()
        }
        // 文字のみの場合
        else if (!options.isNullOrEmpty()) {
            dialog = MultiSelectorDialog.Builder()
                .title(title)
                .options(options)
                .sounds(soundArray)
                .selectable(selectableArray)
                .columns(columns)
                .graphWidth(bitmapWidth)
                .graphHeight(bitmapHeight)
                .positiveLabel("OK")
                .negativeLabel("Cancel")
                .initSelect(checkArray)
                .requestCode(requestCode)
                .build()
        }
        // 画像のみの場合
        else if (!optionBitmaps.isNullOrEmpty()) {
            dialog = MultiSelectorDialog.Builder()
                .title(title)
                .bitmaps(optionBitmaps)
                .sounds(soundArray)
                .selectable(selectableArray)
                .columns(columns)
                .graphWidth(bitmapWidth)
                .graphHeight(bitmapHeight)
                .positiveLabel("OK")
                .negativeLabel("Cancel")
                .initSelect(checkArray)
                .requestCode(requestCode)
                .build()
        }
        // 両方とも存在しない場合(既にエラーになっているが)
        else {
            throw IllegalArgumentException()
        }
        return dialog
    }
}