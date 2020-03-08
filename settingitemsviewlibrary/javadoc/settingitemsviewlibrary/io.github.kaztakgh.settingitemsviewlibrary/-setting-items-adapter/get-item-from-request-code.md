[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [SettingItemsAdapter](index.md) / [getItemFromRequestCode](./get-item-from-request-code.md)

# getItemFromRequestCode

`fun getItemFromRequestCode(requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ItemInterface`](../-item-interface/index.md)`?`

リクエストコードからアダプター内アイテムを取得する
取得した際にアダプター内の状態を利用するには、Activity/Fragmentでキャストを行うこと
リクエストコードを使用しないアイテムの場合はnullを返す(v1.2.2より)

### Parameters

`requestCode` - リクエストコード

**Since**
v1.2.0

**Return**
アダプター内アイテム|null

