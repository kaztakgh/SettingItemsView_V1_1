/**
 * @file SwitchItemViewHolder.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.view.View
import android.widget.Switch

/**
 * 状態をON/OFFで指定するアイテムのビューの表示に使用する
 *
 * @constructor
 * スイッチを使用するアイテムのレイアウトを呼び出す
 *
 * @param view viewのレイアウトリソース
 */
internal class SwitchItemViewHolder(
    view: View
) : NormalItemViewHolder(view) {
    companion object {
        /**
         * スイッチがONの時のテキスト(デフォルト)
         */
        const val STATE_ON_TEXT_DEFAULT = "ON"
        /**
         * スイッチがOFFの時のテキスト(デフォルト)
         */
        const val STATE_OFF_TEXT_DEFAULT = "OFF"
    }
    private val stateSwitch: Switch = view.findViewById(R.id.sw_state)

    /**
     * 選択可能であるか
     */
    override var isSelectable: Boolean = true
        get() = super.isSelectable
        set(value) {
            field = value
            // 選択可能であるかどうかを透明度で示す
            // 選択可能な場合は全てのパーツを不透明、選択不可能な場合は全てのパーツを半透明に変更する
            val alpha: Float = if (value) 1.0f else 0.5f
            titleView.alpha = alpha
            textView.alpha = alpha
            if (iconView.visibility == View.VISIBLE)
                iconView.alpha = alpha

            // 選択不可能である場合はスイッチの操作をすることが出来ないようにする
            stateSwitch.isClickable = value
            stateSwitch.isEnabled = value
        }

    /**
     * チェック
     */
    var isChecked: Boolean
        get() = stateSwitch.isChecked
        set(value) {
            // チェック状態によるスイッチとテキストの変更
            stateSwitch.isChecked = value
            text = if (value) textOnTrue else textOnFalse
        }

    /**
     * ONの時のテキスト
     * 空文字列は登録できないようにする
     */
    var textOnTrue: String = STATE_ON_TEXT_DEFAULT
        set(value) {
            if (value.isNotEmpty()) field = value
        }

    /**
     * OFFの時のテキスト
     * 空文字列は登録できないようにする
     */
    var textOnFalse: String = STATE_OFF_TEXT_DEFAULT
        set(value) {
            if (value.isNotEmpty()) field = value
        }

    /**
     * スイッチ要素の取得
     * 他の要素に影響を与える機能を持つため、clickListenerをAdapterで記述する
     *
     * @return Switch
     */
    fun getStateSwitch(): Switch = stateSwitch
}