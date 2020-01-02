[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [SettingItemsAdapter](index.md) / [getItemViewType](./get-item-view-type.md)

# getItemViewType

`fun getItemViewType(position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)

Return the view type of the item at `position` for the purposes
of view recycling.

The default implementation of this method returns 0, making the assumption of
a single view type for the adapter. Unlike ListView adapters, types need not
be contiguous. Consider using id resources to uniquely identify item view types.

### Parameters

`position` - position to query

**Return**
integer ビューの種類を数値で指定

