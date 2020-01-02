/**
 * @file InputTextItemViewHolder.kt
 */
package io.github.kaztakgh.settingitemsviewlibrary

import android.text.InputType
import android.view.View
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.constraintlayout.widget.Group

/**
 * 状態を編集可能な文字列で指定するアイテムのビューの表示に使用する
 *
 * @constructor
 * エディタを使用するアイテムのレイアウトを呼び出す
 *
 * @param view viewのレイアウトリソース
 */
internal class InputTextItemViewHolder(
    view: View
) : NormalItemViewHolder(view) {
    companion object {
        /**
         * パラメータ省略時の文字列の長さ
         */
        const val INIT_LENGTH = 64
    }
    private val editor: EditText = view.findViewById(R.id.et_input)
    private val confirmButton: Button = view.findViewById(R.id.btn_confirm)
    private val dropdown: ImageView = view.findViewById(R.id.iv_dropdown)
    private val editGrp: Group = view.findViewById(R.id.grp_edit)

    /**
     * テキスト
     */
    override var text : String
        get() {
            return textView.text.toString()
        }
        set(value) {
            textView.text = value
            // 入力タイプがパスワードの場合
            if (inputType == InputType.TYPE_NUMBER_VARIATION_PASSWORD
                || inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD
                || inputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                || inputType == InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD) {
                textView.text = "*".repeat(length)
            }
        }

    /**
     * 選択可能であるか
     */
    override var isSelectable : Boolean
        get() = editor.isEnabled
        set(value) {
            editor.isEnabled = value
            // 選択可能であるかどうかを透明度で示す
            // 選択可能な場合は全てのパーツを不透明、選択不可能な場合は全てのパーツを半透明に変更する
            val alpha: Float = if (value) 1.0f else 0.5f
            textView.alpha = alpha
            titleView.alpha = alpha
            dropdown.alpha = alpha
            editor.alpha = alpha
            confirmButton.alpha = alpha
        }

    /**
     * テキストの長さ
     */
    var length: Int = INIT_LENGTH
        set(value) {
            field = value
            editor.width = value
        }

    /**
     * 入力形式
     * InputTypeクラスの変数を使用すること
     */
    var inputType: Int = InputType.TYPE_CLASS_TEXT
        set(value) {
            field = value
            editor.inputType = value
        }

    /**
     * エディタ部分の取得
     *
     * @return et_input
     */
    fun getEditor() : EditText = editor

    /**
     * テキストを編集するエリアの取得
     *
     * @return grp_edit
     */
    fun getEditorArea() : Group = editGrp

    /**
     * 入力文字列を確定するボタンの取得
     *
     * @return btn_confirm
     */
    fun getConfirmButton() : Button = confirmButton

    /**
     * ドロップダウンのアイコンを取得
     *
     * @return iv_dropdown
     */
    fun getDropDownIcon() : ImageView = dropdown

    /**
     * 右側の矢印の回転
     *
     * @param isExpand true: 編集部分を拡張, false: 編集部分を閉じる
     */
    fun rotateDropDown(isExpand: Boolean) {
        val fromDegree = if (isExpand) 0.0f else 180.0f
        val toDegree = if (isExpand) 180.0f else 0.0f
        val rotate = RotateAnimation(
            fromDegree, toDegree,
            RELATIVE_TO_SELF, 0.5f,
            RELATIVE_TO_SELF, 0.5f
        )

        // animation時間 msec
        rotate.duration = 100
        // animationが終わったそのまま表示にする
        rotate.fillAfter = true

        //アニメーションの開始
        dropdown.startAnimation(rotate)
    }
}