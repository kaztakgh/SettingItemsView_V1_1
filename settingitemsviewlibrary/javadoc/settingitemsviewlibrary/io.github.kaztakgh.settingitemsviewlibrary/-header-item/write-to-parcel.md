[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [HeaderItem](index.md) / [writeToParcel](./write-to-parcel.md)

# writeToParcel

`fun writeToParcel(dest: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`?, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Flatten this object in to a Parcel.

### Parameters

`dest` - The Parcel in which the object should be written.

`flags` - Additional flags about how the object should be written.
May be 0 or [.PARCELABLE_WRITE_RETURN_VALUE](#).