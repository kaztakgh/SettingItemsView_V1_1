/**
 * @file SettingItemsView.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SettingItemsView : RecyclerView {
    /**
     * ItemInterface型のArrayList
     * 表示するアイテムを挿入することで画面上に表示される
     */
    var itemsList: ArrayList<ItemInterface> = ArrayList()

    /**
     * Fragment上で表示する場合に、現在のFragmentを指定する
     */
    var fragment: Fragment? = null

    /**
     * Fragmentを使用する際、FragmentManagerも指定すること
     */
    var fragmentManager: FragmentManager? = null

    /**
     * 表示用のアダプター
     */
    private lateinit var itemsAdapter: SettingItemsAdapter
    // スクロール位置(アクティビティの復帰対策)
    private var scrollPosition: Int = 0
    private var scrollPositionY: Int = 0

    /**
     * コンストラクタ
     */
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    /**
     * 画面にセットしたときに行う処理
     * @exception IllegalArgumentException itemsListのタグが重複しているものがあった場合に発生
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        // itemsListのタグが重複していないかのチェック
        check(isUniqueTag(itemsList)) {context.resources.getText(R.string.unique_tag_exception)}
        // アダプターのセット
        layoutManager = LinearLayoutManager(context)
        itemsAdapter = SettingItemsAdapter(itemsList, fragment, fragmentManager)
        adapter = itemsAdapter
        // 区切り線のセット
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        addItemDecoration(itemDecoration)
        // スクロール位置調節
        scrollToPosition(scrollPosition)
    }

    /**
     * 画面から離れるときに行う処理
     */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        adapter = null
    }

    /**
     * 回転などで状態を保存するときの処理
     *
     * @return SavedState
     */
    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        // 各要素の保存
        // 異なるクラスのアイテムを保存するため、各要素でクラスの取得とそれに対する保存を行う
        itemsAdapter.itemsList.forEach {
            when (it) {
                // ヘッダー
                is HeaderItem -> {
                    val item : HeaderItem = it
                    bundle.putParcelable(item.tag, item)
                }
                // スイッチ
                is SwitchItem -> {
                    val item : SwitchItem = it
                    bundle.putParcelable(item.tag, item)
                }
                // スピナー
                is SpinnerItem -> {
                    val item : SpinnerItem = it
                    bundle.putParcelable(item.tag, item)
                }
                // 単一選択
                is SingleSelectItem -> {
                    val item : SingleSelectItem = it
                    bundle.putParcelable(item.tag, item)
                }
                // 複数選択
                is MultiSelectItem -> {
                    val item : MultiSelectItem = it
                    bundle.putParcelable(item.tag, item)
                }
                // 数値選択
                is SeekBarItem -> {
                    val item : SeekBarItem = it
                    bundle.putParcelable(item.tag, item)
                }
                // 日付
                is DateItem -> {
                    val item : DateItem = it
                    bundle.putParcelable(item.tag, item)
                }
                // 時刻
                is TimeItem -> {
                    val item : TimeItem = it
                    bundle.putParcelable(item.tag, item)
                }
                // 文字入力
                is InputTextItem -> {
                    val item : InputTextItem = it
                    bundle.putParcelable(item.tag, item)
                }
                // ファイル選択
                is StorageFileSelectItem -> {
                    val item : StorageFileSelectItem = it
                    bundle.putParcelable(item.tag, item)
                }
            }
        }
        val parent = super.onSaveInstanceState()
        val saved = SavedState(parent!!)
        // 現在の状態を保存する
        saved.bundle = bundle

        // スクロール位置の保存
        val pos = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        saved.scrollPosition = pos
        if (adapter == null || adapter!!.itemCount == 0) {
            saved.scrollPositionY = 0
        } else {
            saved.scrollPositionY = getChildAt(0).top
        }
        return saved
    }

    /**
     * 状態を復元するときの処理
     *
     * @param state onSaveStateInstanceで保存した内容
     */
    override fun onRestoreInstanceState(state: Parcelable?) {
        val saved = state as SavedState
        super.onRestoreInstanceState(saved.superState)
        // bundleに保存した内容をリストにコピーする
        // bundleのkeyの値を見て、リストの値の番号にあったところにオブジェクトを挿入する
        // listの中身はnullになっていないので、listに対して値を上書きする
        // (復元時はonAttachedToWindowより先に呼ばれるため)
        val tagArray: List<String> = itemsList.map { it.tag }
        saved.bundle!!.keySet().forEach {
            // 位置の検出
            val index = tagArray.indexOf(it)
            when (itemsList[index]) {
                // ヘッダー
                is HeaderItem -> {
                }
                // スイッチ
                is SwitchItem -> {
                    val savedItem : SwitchItem = saved.bundle!![it] as SwitchItem
                    val listItem : SwitchItem = itemsList[index] as SwitchItem
                    listItem.enabled = savedItem.enabled
                    listItem.checked = savedItem.checked
                    itemsList[index] = listItem
                }
                // スピナー
                is SpinnerItem -> {
                    val savedItem : SpinnerItem = saved.bundle!![it] as SpinnerItem
                    val listItem : SpinnerItem = itemsList[index] as SpinnerItem
                    listItem.enabled = savedItem.enabled
                    listItem.select = savedItem.select
                    itemsList[index] = listItem
                }
                // 単一選択
                is SingleSelectItem -> {
                    val savedItem : SingleSelectItem = saved.bundle!![it] as SingleSelectItem
                    val listItem : SingleSelectItem = itemsList[index] as SingleSelectItem
                    listItem.enabled = savedItem.enabled
                    listItem.select = savedItem.select
                    itemsList[index] = listItem
                }
                // 複数選択
                is MultiSelectItem -> {
                    val savedItem : MultiSelectItem = saved.bundle!![it] as MultiSelectItem
                    val listItem : MultiSelectItem = itemsList[index] as MultiSelectItem
                    listItem.enabled = savedItem.enabled
                    listItem.select = savedItem.select
                    itemsList[index] = listItem
                }
                // 数値選択
                is SeekBarItem -> {
                    val savedItem : SeekBarItem = saved.bundle!![it] as SeekBarItem
                    val listItem : SeekBarItem = itemsList[index] as SeekBarItem
                    listItem.enabled = savedItem.enabled
                    listItem.state = savedItem.state
                    itemsList[index] = listItem
                }
                // 日付
                is DateItem -> {
                    val savedItem : DateItem = saved.bundle!![it] as DateItem
                    val listItem : DateItem = itemsList[index] as DateItem
                    listItem.enabled = savedItem.enabled
                    listItem.date = savedItem.date
                    itemsList[index] = listItem
                }
                // 時刻
                is TimeItem -> {
                    val savedItem : TimeItem = saved.bundle!![it] as TimeItem
                    val listItem : TimeItem = itemsList[index] as TimeItem
                    listItem.enabled = savedItem.enabled
                    listItem.time = savedItem.time
                    itemsList[index] = listItem
                }
                // 文字入力
                is InputTextItem -> {
                    val savedItem : InputTextItem = saved.bundle!![it] as InputTextItem
                    val listItem : InputTextItem = itemsList[index] as InputTextItem
                    listItem.enabled = savedItem.enabled
                    listItem.text = savedItem.text
                    itemsList[index] = listItem
                }
                // ファイル選択
                is StorageFileSelectItem -> {
                    val savedItem : StorageFileSelectItem = saved.bundle!![it] as StorageFileSelectItem
                    val listItem : StorageFileSelectItem = itemsList[index] as StorageFileSelectItem
                    listItem.enabled = savedItem.enabled
                    listItem.uri = savedItem.uri
                    itemsList[index] = listItem
                }
            }
        }
        scrollPosition = saved.scrollPosition
        scrollPositionY = saved.scrollPositionY
        saved.bundle!!.clear()
    }

    /**
     * adapterの取得
     *
     * @return [SettingItemsAdapter] SettingItemsAdapter
     */
    fun getSettingItemsAdapter(): SettingItemsAdapter = adapter as SettingItemsAdapter

    /**
     * タグの重複チェック
     *
     * @param list 表示に使用するリスト
     * @return true: 重複なし, false: 重複あり
     */
    private fun isUniqueTag(list: ArrayList<ItemInterface>): Boolean {
        val distinctTagArray: List<String> = list.map { it.tag }.distinct()
        return distinctTagArray.size == list.size
    }

    /**
     * ビューの状態を保存する
     */
    internal class SavedState : BaseSavedState {
        // 保存する変数
        var bundle: Bundle? = null
        var scrollPosition: Int = 0
        var scrollPositionY: Int = 0

        constructor(source: Parcel): super(source) {
            bundle = source.readBundle(javaClass.classLoader)
            scrollPosition = source.readInt()
            scrollPositionY = source.readInt()
        }

        constructor(superState: Parcelable): super(superState)

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeBundle(bundle)
            out.writeInt(scrollPosition)
            out.writeInt(scrollPositionY)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(parcel: Parcel): SavedState {
                return SavedState(parcel)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }
    }
}