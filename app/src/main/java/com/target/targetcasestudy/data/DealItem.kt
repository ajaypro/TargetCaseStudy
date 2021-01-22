package com.target.targetcasestudy.data

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @keep - To avoid been obfuscated by proguard.
 */
@Keep
data class Deals(
  @SerializedName("products")
  val products: List<DealItem>
)
@Keep
@Parcelize
data class DealItem (
    @SerializedName("id")
  var id: Int,

    @SerializedName("title")
  var title: String = "",

    @SerializedName("aisle")
    var aisle: String = "",

    @SerializedName("description")
  var description: String = "",

    @SerializedName("image_url")
    var img_url: String = "",

    @SerializedName("regular_price")
  var regularPrice: RegularPrice? = null,

    @SerializedName("sale_price")
    var salePrice: SalePrice? = null,

): Parcelable
@Keep
@Parcelize
data class RegularPrice(
  @SerializedName("amount_in_cents")
  val amount_in_cents: Int,

  @SerializedName("currency_symbol")
  val currency_symbol: String ="",

  @SerializedName("display_string")
  val display_string: String =""
):Parcelable

@Keep
@Parcelize
data class SalePrice(

  @SerializedName("amount_in_cents")
  val amount_in_cents: Int,

  @SerializedName("currency_symbol")
  val currency_symbol: String ="",

  @SerializedName("display_string")
  val display_string: String =""
):Parcelable