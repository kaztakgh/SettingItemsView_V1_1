/**
 * @file SettingItemsAdapter.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import io.github.kaztakgh.viewhelper.ViewHelper

/**
 * RecyclerViewにアイテムを表示するためのアダプター
 *
 * @property itemsList Viewで表示するアイテムのリスト
 * @property fragment 使用する対象のFragment
 * @property fragmentManager fragmentが所有するFragmentManager
 */
class SettingItemsAdapter(
    internal var itemsList: ArrayList<ItemInterface>,
    private var fragment: Fragment? = null,
    private var fragmentManager: FragmentManager? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        /**
         * ヘッダー
         */
        const val VIEW_TYPE_HEADER: Int = 0
        /**
         * 通常(タイトルとテキスト)
         */
        const val VIEW_TYPE_TITLE_AND_TEXT: Int = 1
        /**
         * スイッチによる選択
         */
        const val VIEW_TYPE_SWITCH: Int = 2
        /**
         * スピナーによる選択
         */
        const val VIEW_TYPE_SPINNER: Int = 3
        /**
         * シークバーでの数値選択
         */
        const val VIEW_TYPE_SEEKBAR: Int = 4
        /**
         * 文字入力
         */
        const val VIEW_TYPE_INPUT_TEXT: Int = 5
    }
    private lateinit var context: Context
    private var itemsView: RecyclerView? = null

    /**
     * Called by RecyclerView when it starts observing this Adapter.
     *
     *
     * Keep in mind that same adapter may be observed by multiple RecyclerViews.
     *
     * @param recyclerView The RecyclerView instance which started observing this adapter.
     * @see .onDetachedFromRecyclerView
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        itemsView = recyclerView
    }

    /**
     * Called by RecyclerView when it stops observing this Adapter.
     *
     * @param recyclerView The RecyclerView instance which stopped observing this adapter.
     * @see .onAttachedToRecyclerView
     */
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        itemsView = null
        super.onDetachedFromRecyclerView(recyclerView)
    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        when(viewType) {
            // ヘッダー
            VIEW_TYPE_HEADER -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.header_item, parent, false)
                return HeaderItemViewHolder(view)
            }
            // スイッチ
            VIEW_TYPE_SWITCH -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.switch_item, parent, false)
                val holder = SwitchItemViewHolder(view)
                // クリックしたときの処理
                view.setOnClickListener { v ->
                    itemsView.let {
                        holder.itemClickListener?.onItemClick(v, it!!.getChildAdapterPosition(v))
                    }
                }
                return holder
            }
            // スピナー
            VIEW_TYPE_SPINNER -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.spinner_item, parent, false)
                return SpinnerItemViewHolder(view)
            }
            // シークバー
            VIEW_TYPE_SEEKBAR -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.seekbar_item, parent, false)
                return SeekBarItemViewHolder(view)
            }
            // 文字入力
            VIEW_TYPE_INPUT_TEXT -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.input_text_item, parent, false)
                val holder = InputTextItemViewHolder(view)
                // クリックしたときの処理
                view.setOnClickListener { v ->
                    itemsView.let {
                        holder.itemClickListener?.onItemClick(v, it!!.getChildAdapterPosition(v))
                    }
                }
                return holder
            }
            // それ以外
            else -> {
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.normal_item, parent, false)
                val holder = NormalItemViewHolder(view)
                // クリックしたときの処理
                view.setOnClickListener { v ->
                    itemsView.let {
                        holder.itemClickListener?.onItemClick(v, it!!.getChildAdapterPosition(v))
                    }
                }
                return holder
            }
        }
    }

    /**
     * Return the view type of the item at `position` for the purposes
     * of view recycling.
     *
     *
     * The default implementation of this method returns 0, making the assumption of
     * a single view type for the adapter. Unlike ListView adapters, types need not
     * be contiguous. Consider using id resources to uniquely identify item view types.
     *
     * @param position position to query
     * @return integer ビューの種類を数値で指定
     */
    override fun getItemViewType(position: Int): Int {
        // アイテムの型によって表示する方法を変更する
        return when(itemsList[position]) {
            // ヘッダー
            is HeaderItem -> VIEW_TYPE_HEADER
            // スイッチ
            is SwitchItem -> VIEW_TYPE_SWITCH
            // スピナー
            is SpinnerItem -> VIEW_TYPE_SPINNER
            // シークバー
            is SeekBarItem -> VIEW_TYPE_SEEKBAR
            // 文字入力
            is InputTextItem -> VIEW_TYPE_INPUT_TEXT
            // それ以外
            else -> VIEW_TYPE_TITLE_AND_TEXT
        }
    }

    /**
     * アダプターに登録されたリストの要素数を返す
     * @return アダプターに登録されたリストの要素数
     */
    override fun getItemCount(): Int {
        return itemsList.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder アイテムを表示するビューホルダー
     * @param position アイテムの表示位置(0から開始)
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = itemsList[position]) {
            // ヘッダー
            is HeaderItem -> {
                val vh: HeaderItemViewHolder = holder as HeaderItemViewHolder
                val updateItem: HeaderItem = item
                bindHeaderItemViewHolder(vh, updateItem)
            }
            // スイッチ
            is SwitchItem -> {
                val vh: SwitchItemViewHolder = holder as SwitchItemViewHolder
                val updateItem: SwitchItem = item
                bindSwitchItemViewHolder(vh, updateItem)
            }
            // スピナー
            is SpinnerItem -> {
                val vh: SpinnerItemViewHolder = holder as SpinnerItemViewHolder
                val updateItem: SpinnerItem = item
                bindSpinnerItemViewHolder(vh, updateItem)
            }
            // 単一選択
            is SingleSelectItem -> {
                val vh: NormalItemViewHolder = holder as NormalItemViewHolder
                val updateItem: SingleSelectItem = item
                bindSingleSelectItemViewHolder(vh, updateItem)
            }
            // 複数選択
            is MultiSelectItem -> {
                val vh: NormalItemViewHolder = holder as NormalItemViewHolder
                val updateItem: MultiSelectItem = item
                bindMultiSelectItemViewHolder(vh, updateItem)
            }
            // 数値選択
            is SeekBarItem -> {
                val vh: SeekBarItemViewHolder = holder as SeekBarItemViewHolder
                val updateItem: SeekBarItem = item
                bindSeekBarItemViewHolder(vh, updateItem)
            }
            // 日付
            is DateItem -> {
                val vh: NormalItemViewHolder = holder as NormalItemViewHolder
                val updateItem: DateItem = item
                bindDateItemViewHolder(vh, updateItem)
            }
            // 時刻
            is TimeItem -> {
                val vh: NormalItemViewHolder = holder as NormalItemViewHolder
                val updateItem: TimeItem = item
                bindTimeItemViewHolder(vh, updateItem)
            }
            // 文字列
            is InputTextItem -> {
                val vh: InputTextItemViewHolder = holder as InputTextItemViewHolder
                val updateItem: InputTextItem = item
                bindInputTextItemViewHolder(vh, updateItem)
            }
            // ファイル選択
            is StorageFileSelectItem -> {
                val vh: NormalItemViewHolder = holder as NormalItemViewHolder
                val updateItem: StorageFileSelectItem = item
                bindFileSelectItemViewHolder(vh, updateItem)
            }
            else -> {}
        }
    }

    /**
     * リストのデータを取得する
     * @since v1.2.1
     *
     * @return Viewで表示するアイテムのリスト
     */
    fun getItemsList() : ArrayList<ItemInterface> = itemsList

    /**
     * 指定したタグを持つアイテムの更新
     * @since v1.2.0
     * @exception IllegalArgumentException itemsListのタグを変更しようとした場合に発生
     *
     * @param tag 更新元のアイテムのタグ
     * @param item 更新するアイテム<br>更新元のアイテムのタグと一致させる必要がある
     */
    fun update(tag: String, item: ItemInterface) {
        // 更新箇所の検索
        // itemsListはタグを一意にする必要があるため、indexOfFirstで検索可能
        val index = itemsList.indexOfFirst { it.tag == tag }
        // 該当する箇所がない場合は処理を終了
        if (index < 0) return
        // 更新アイテムのタグと指定タグが異なる場合はエラー処理を行う
        check(index >= 0 && itemsList[index].tag == item.tag)

        // itemsListの更新
        itemsList[index] = item

        // 表示を更新
        notifyItemChanged(index)
    }

    /**
     * リクエストコードからアダプター内アイテムを取得する
     * 取得した際にアダプター内の状態を利用するには、Activity/Fragmentでキャストを行うこと
     * リクエストコードを使用しないアイテムの場合はnullを返す(v1.2.2より)
     * @since v1.2.0
     *
     * @param requestCode リクエストコード
     * @return アダプター内アイテム|null
     */
    fun getItemFromRequestCode(requestCode: Int): ItemInterface? {
        // リクエストコードの配列を取得する
        val reqCodeArray: ArrayList<Int?> = requestCodeArray()
        // 該当するリクエストコードの位置を取得する
        val itemPos: Int = reqCodeArray.indexOf(requestCode)
        return if (itemPos < 0) null else itemsList[itemPos]
    }

    /**
     * HeaderItemの内容をitemから指定する
     *
     * @param holder アイテムを表示するビューホルダー
     * @param item 表示対象のHeaderItem
     */
    private fun bindHeaderItemViewHolder(holder: HeaderItemViewHolder, item: HeaderItem) {
        // 表示内容の指定
        holder.headerText = item.title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.textColor = ViewHelper.getColorAttr(context, android.R.attr.colorAccent)
        }
    }

    /**
     * SwitchItemの表示内容をitemから指定する
     *
     * @param holder アイテムを表示するビューホルダー
     * @param item 表示対象のSwitchItem
     */
    private fun bindSwitchItemViewHolder(holder: SwitchItemViewHolder, item: SwitchItem) {
        // 表示内容の指定
        holder.title = item.title
        holder.textOnTrue = item.textOnTrue
        holder.textOnFalse = item.textOnFalse
        holder.bmpIcon = item.bmpIcon
        holder.isSelectable = item.enabled
        holder.isChecked = item.checked
        val position: Int = holder.adapterPosition
        holder.itemClickListener = object : NormalItemViewHolder.ItemClickListener {
            /**
             * アイテムをクリックしたときの処理
             *
             * @param view レイアウトビュー
             * @param position アダプター内のアイテムの位置
             */
            override fun onItemClick(view: View, position: Int) {
                // スイッチの状態を変更する
                changeSwitchCheck(holder, item, position)
            }
        }
        holder.getStateSwitch().setOnClickListener {
            // スイッチの状態を変更する
            changeSwitchCheck(holder, item, position)
        }
    }

    /**
     * スイッチの状態が変化したときの処理
     *
     * @param holder SwitchItemViewHolder
     * @param item SwitchItem
     * @param position アダプター内のリストの順序
     */
    private fun changeSwitchCheck(holder: SwitchItemViewHolder, item: SwitchItem, position: Int) {
        val listItem: SwitchItem = itemsList[position] as SwitchItem
        // スイッチの操作ができない状態の場合はここで終了
        // (returnをここで指定しているのは、2通りの操作で変更できてしまうため)
        if (!listItem.enabled) return

        // スイッチの状態の変更
        val checked: Boolean = !item.checked
        holder.isChecked = checked
        listItem.checked = checked
        // 変更後の処理がある場合
        if (item.stateChangedListener != null) {
            item.stateChangedListener!!.onSwitchCheckChange(this, checked)
        }
    }

    /**
     * SpinnerItemの表示内容をitemから指定する
     *
     * @param holder アイテムを表示するビューホルダー
     * @param item 表示対象のSpinnerItem
     */
    private fun bindSpinnerItemViewHolder(holder: SpinnerItemViewHolder, item: SpinnerItem) {
        // 表示内容の指定
        holder.title = item.title
        holder.bmpIcon = item.bmpIcon
        holder.isSelectable = item.enabled
        // 選択肢の設定
        val optionsSpinner: Spinner = holder.getOptionsSpinner()
        setSpinnerOptions(optionsSpinner, item.options)
        optionsSpinner.setSelection(item.select)
        optionsSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            /**
             * 何も選択しなかった場合
             * もしくはSpinnerの外側をタップした場合
             *
             * @param parent The AdapterView that now contains no selected item.
             */
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            /**
             * Spinnerで表示しているアイテムを選択した場合の処理
             *
             * @param parent The AdapterView where the selection happened
             * @param view The view within the AdapterView that was clicked
             * @param position アダプターの選択肢の位置
             * @param id 選択した列のID
             */
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // アイテムの選択を変更
                (itemsList[holder.adapterPosition] as SpinnerItem).select = position
                // 変更後の処理がある場合
                if (item.selectChangeListener != null && item.focusable) {
                    item.selectChangeListener!!.onItemSelectChanged(this@SettingItemsAdapter, position)
                }
                // 初回操作時
                when {
                    !item.focusable -> item.focusable = true
                }
            }
        }
    }

    /**
     * スピナーの選択肢のセット
     *
     * @param spinner セット対象のスピナー
     * @param options 選択肢
     */
    private fun setSpinnerOptions(spinner: Spinner, options: Array<String>) {
        val spinnerAdapter = ArrayAdapter(this.context, android.R.layout.simple_spinner_item, options)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
    }

    /**
     * SingleSelectItemの表示内容をitemから指定する
     *
     * @param holder アイテムを表示するビューホルダー
     * @param item 表示対象のSingleSelectItem
     */
    private fun bindSingleSelectItemViewHolder(holder: NormalItemViewHolder, item: SingleSelectItem) {
        // 表示内容の指定
        holder.title = item.title
        holder.bmpIcon = item.bmpIcon
        holder.isSelectable = item.enabled
        // 選択肢(文字)がある場合、選択したアイテムをテキストに出力
        if (!item.options.isNullOrEmpty()) {
            holder.text = item.options[item.select]
        }
        // アイテムが無効時の場合は処理をダイアログ出力を行わない
        if (!item.enabled) return

        holder.itemClickListener = object : NormalItemViewHolder.ItemClickListener{
            /**
             * アイテムをクリックしたときの処理
             *
             * @param view レイアウトビュー
             * @param position アダプター内のアイテムの位置
             */
            override fun onItemClick(view: View, position: Int) {
                // ダイアログ出力
                showSingleSelectDialog(item)
            }
        }
    }

    /**
     * ダイアログを出力する
     *
     * @param item SingleSelectItem
     */
    private fun showSingleSelectDialog(item: SingleSelectItem) {
        // ダイアログの出力
        // 実装元がfragmentの場合
        if (fragment != null && fragmentManager != null) {
            item.buildDialog().show(fragment!!, fragmentManager!!)
        }
        // 実装元がactivityの場合
        else {
            item.buildDialog().show(context)
        }
    }

    /**
     * MultiSelectItemの表示内容をitemから指定する
     *
     * @param holder アイテムを表示するビューホルダー
     * @param item 表示対象のMultiSelectItem
     */
    private fun bindMultiSelectItemViewHolder(holder: NormalItemViewHolder, item: MultiSelectItem) {
        // 表示内容の指定
        holder.title = item.title
        holder.bmpIcon = item.bmpIcon
        holder.isSelectable = item.enabled
        // 選択肢(文字)がある場合、選択したアイテムをテキストに出力
        if (!item.options.isNullOrEmpty()) {
            holder.text = if (item.text.isBlank()) item.nothingsSelectString else item.text
        }
        // アイテムが無効時の場合は処理をダイアログ出力を行わない
        if (!item.enabled) return

        holder.itemClickListener = object : NormalItemViewHolder.ItemClickListener{
            /**
             * アイテムをクリックしたときの処理
             *
             * @param view レイアウトビュー
             * @param position アダプター内のアイテムの位置
             */
            override fun onItemClick(view: View, position: Int) {
                // ダイアログ出力
                showMultiSelectDialog(item)
            }
        }
    }

    /**
     * ダイアログを出力する
     *
     * @param item MultiSelectItem
     */
    private fun showMultiSelectDialog(item: MultiSelectItem) {
        // ダイアログの出力
        // 実装元がfragmentの場合
        if (fragment != null && fragmentManager != null) {
            item.buildDialog().show(fragment!!, fragmentManager!!)
        }
        // 実装元がactivityの場合
        else {
            item.buildDialog().show(context)
        }
    }

    /**
     * SeekBarItemの表示内容をitemから指定する
     *
     * @param holder アイテムを表示するビューホルダー
     * @param item 表示対象のSeekBarItem
     */
    private fun bindSeekBarItemViewHolder(holder: SeekBarItemViewHolder, item: SeekBarItem) {
        // 表示内容の指定
        holder.title = item.title
        holder.bmpIcon = item.bmpIcon
        holder.isSelectable = item.enabled
        // 設定値を配列形式に変換
        val paramsArray: IntArray = item.createParamsArray()
        // SeekBarの最小値は0から始まるため、最大値は配列のサイズ-1に設定する
        holder.max = paramsArray.size - 1
        // 設定した数値から位置を探す
        // (見つからない場合はつまみを一番左に置く)
        val arrayPos: Int = paramsArray.indexOf(item.state)
        holder.state = if (arrayPos > -1) arrayPos else 0
        holder.text = createSeekBarStatusText(paramsArray[holder.state], item.unit)
        val stateBar: SeekBar = holder.getStateBar()
        // アイテムが無効時の場合は以降の処理を行わない
        if (!item.enabled) return

        // バー操作時の挙動
        stateBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            /**
             * Notification that the progress level has changed. Clients can use the fromUser parameter
             * to distinguish user-initiated changes from those that occurred programmatically.
             *
             * @param seekBar The SeekBar whose progress has changed
             * @param progress The current progress level. This will be in the range min..max where min
             * and max were set by [ProgressBar.setMin] and
             * [ProgressBar.setMax], respectively. (The default values for
             * min is 0 and max is 100.)
             * @param fromUser True if the progress change was initiated by the user.
             */
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // アイテムの数値を変更
                item.state = paramsArray[progress]
                // 数値の表示
                holder.text = item.state.toString()
                holder.text = createSeekBarStatusText(paramsArray[holder.state], item.unit)
            }

            /**
             * Notification that the user has started a touch gesture. Clients may want to use this
             * to disable advancing the seekbar.
             * @param seekBar The SeekBar in which the touch gesture began
             */
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            /**
             * Notification that the user has finished a touch gesture. Clients may want to use this
             * to re-enable advancing the seekbar.
             * @param seekBar The SeekBar in which the touch gesture began
             */
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // 変更後の動作
                if (item.valueChangeListener != null) {
                    item.valueChangeListener!!.onItemValueChange(this@SettingItemsAdapter, item.state)
                }
            }
        })
    }

    /**
     * SeekBarで表示するテキストの作成
     *
     * @param value 数値
     * @param unit 単位
     * @return 数値と単位のテキスト
     */
    private fun createSeekBarStatusText(value: Int, unit: String) : String {
        var text: String = value.toString()
        if (unit.isNotEmpty()) text += unit
        return text
    }

    /**
     * DateItemの表示内容をitemから指定する
     *
     * @param holder アイテムを表示するビューホルダー
     * @param item 表示対象のDateItem
     */
    private fun bindDateItemViewHolder(holder: NormalItemViewHolder, item: DateItem) {
        // 表示内容の指定
        holder.title = item.title
        holder.bmpIcon = item.bmpIcon
        holder.isSelectable = item.enabled
        // 日付の出力
        holder.text = item.text
        // アイテムが無効時の場合は処理をダイアログ出力を行わない
        if (!item.enabled) return

        // アイテムをクリックしたときの処理
        holder.itemClickListener = object : NormalItemViewHolder.ItemClickListener {
            /**
             * アイテムをクリックしたときの処理
             *
             * @param view レイアウトビュー
             * @param position アダプター内のアイテムの位置
             */
            override fun onItemClick(view: View, position: Int) {
                showDateDialog(item)
            }
        }
    }

    /**
     * ダイアログを出力する
     *
     * @param item DateItem
     */
    private fun showDateDialog(item: DateItem) {
        // ダイアログの出力
        // 実装元がfragmentの場合
        if (fragment != null && fragmentManager != null) {
            item.buildDialog().show(fragment!!, fragmentManager!!)
        }
        // 実装元がactivityの場合
        else {
            item.buildDialog().show(context)
        }
    }

    /**
     * TimeItemの表示内容をitemから指定する
     *
     * @param holder アイテムを表示するビューホルダー
     * @param item 表示対象のTimeItem
     */
    private fun bindTimeItemViewHolder(holder: NormalItemViewHolder, item: TimeItem) {
        // 表示内容の指定
        holder.title = item.title
        holder.bmpIcon = item.bmpIcon
        holder.isSelectable = item.enabled
        // 時刻の出力
        holder.text = item.text
        // アイテムが無効時の場合は処理をダイアログ出力を行わない
        if (!item.enabled) return

        // アイテムをクリックしたときの処理
        holder.itemClickListener = object : NormalItemViewHolder.ItemClickListener {
            /**
             * アイテムをクリックしたときの処理
             *
             * @param view レイアウトビュー
             * @param position アダプター内のアイテムの位置
             */
            override fun onItemClick(view: View, position: Int) {
                showTimeDialog(item)
            }
        }
    }

    /**
     * ダイアログを出力する
     *
     * @param item TimeItem
     */
    private fun showTimeDialog(item: TimeItem) {
        // ダイアログの出力
        // 実装元がfragmentの場合
        if (fragment != null && fragmentManager != null) {
            item.buildDialog().show(fragment!!, fragmentManager!!)
        }
        // 実装元がactivityの場合
        else {
            item.buildDialog().show(context)
        }
    }

    /**
     * InputTextItemの表示内容をitemから指定する
     *
     * @param holder アイテムを表示するビューホルダー
     * @param item 表示対象のInputTextItem
     */
    private fun bindInputTextItemViewHolder(holder: InputTextItemViewHolder, item: InputTextItem) {
        // 表示内容の指定
        holder.title = item.title
        holder.bmpIcon = item.bmpIcon
        holder.isSelectable = item.enabled
        holder.text = item.text
        holder.length = item.length
        holder.inputType = item.inputType
        holder.getDropDownIcon().setColorFilter(
            ViewHelper.getColorAttr(context, android.R.attr.textColorPrimary)
        )
        // 編集部分の表示設定(初期値はView.GONE)
        holder.getEditorArea().visibility = GONE
        // アイテムが有効ではない場合はここで処理を終了
        if (!item.enabled) return

        // アイテムをクリックしたときの処理
        holder.itemClickListener = object : NormalItemViewHolder.ItemClickListener {
            /**
             * アイテムをクリックしたときの処理
             *
             * @param view レイアウトビュー
             * @param position アダプター内のアイテムの位置
             */
            override fun onItemClick(view: View, position: Int) {
                // 編集エリアを閉じている場合
                if (holder.getEditorArea().visibility == GONE) {
                    // 編集エリアを表示する
                    holder.rotateDropDown(true)
                    if (item.text.isNotEmpty()) holder.getEditor().setText(item.text)
                    holder.getEditorArea().visibility = VISIBLE
                }
                // 編集エリアを表示している場合
                else {
                    // 文字入力処理を終了
                    finishEditText(holder)
                    holder.text = item.text
                    holder.rotateDropDown(false)
                }
            }
        }
        // 入力文字列を確定する処理
        holder.getConfirmButton().setOnClickListener {
            // 文字入力処理を終了
            finishEditText(holder)
            // 入力文字列をビューに表示
            holder.text = holder.getEditor().text.toString()
            item.text = holder.text
            holder.rotateDropDown(false)
            // 変更後の処理がある場合
            if (item.textChangeListener != null) {
                item.textChangeListener!!.onTextChange(this, item.text)
            }
        }
    }

    /**
     * 文字入力を終了する処理
     *
     * @param holder InputTextItemViewHolder
     */
    private fun finishEditText(holder: InputTextItemViewHolder) {
        // キーボードの表示を終了
        val inputMethodManager: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(holder.itemView.windowToken, 0)
        // 編集エリアを非表示にする
        holder.getEditorArea().visibility = GONE
    }

    /**
     * StorageFileSelectItemの表示内容をitemから指定する
     *
     * @param holder アイテムを表示するビューホルダー
     * @param item 表示対象のStorageFileSelectItem
     */
    private fun bindFileSelectItemViewHolder(holder: NormalItemViewHolder, item: StorageFileSelectItem) {
        // 表示内容の指定
        holder.title = item.title
        holder.bmpIcon = item.bmpIcon
        holder.isSelectable = item.enabled
        // ファイルパスの設定
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            holder.text = if (item.uri != null) item.getContentFilePathDescriptor(context, item.uri!!) else ""
        }
        else {
            holder.text = if (item.uri != null) item.getContentPathStringFromUri(context, item.uri!!) else ""
        }
        // アイテムが有効ではない場合はここで処理を終了
        if (!item.enabled) return

        // クリックされたときの挙動を指定する
        holder.itemClickListener = object : NormalItemViewHolder.ItemClickListener {
            /**
             * アイテムをクリックしたときの処理
             *
             * @param view レイアウトビュー
             * @param position アダプター内のアイテムの位置
             */
            override fun onItemClick(view: View, position: Int) {
                // パーミッションの取得(Android6.0以降)
                item.checkExternalStoragePermission(context)
            }
        }
    }

    /**
     * リクエストコードの配列を作成する
     *
     * @return nullを含む数値型の配列
     */
    private fun requestCodeArray(): ArrayList<Int?> {
        val reqCodeArray: ArrayList<Int?> = ArrayList()
        // 各要素について、requestCodeの項目があるかをクラス名で判断する
        // フィルタリングが困難なため、各要素を調べる方式を取っている
        itemsList.forEach {
            when(it) {
                // 単一選択
                is SingleSelectItem -> {
                    val item : SingleSelectItem = it
                    reqCodeArray.add(item.requestCode)
                }
                // 複数選択
                is MultiSelectItem -> {
                    val item : MultiSelectItem = it
                    reqCodeArray.add(item.requestCode)
                }
                // 日付
                is DateItem -> {
                    val item : DateItem = it
                    reqCodeArray.add(item.requestCode)
                }
                // 時刻
                is TimeItem -> {
                    val item : TimeItem = it
                    reqCodeArray.add(item.requestCode)
                }
                // ファイル選択
                is StorageFileSelectItem -> {
                    val item : StorageFileSelectItem = it
                    reqCodeArray.add(item.requestCode)
                }
                // requestCodeを使用しないアイテム
                else -> {
                    reqCodeArray.add(null)
                }
            }
        }
        return reqCodeArray
    }
}