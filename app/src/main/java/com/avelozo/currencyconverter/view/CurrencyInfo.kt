package com.avelozo.currencyconverter.view

import com.avelozo.currencyconverter.R
import java.lang.Exception

enum  class CurrencyInfo(val code : String, val currencyName: Int, val flag : Int) {

    AUD("AUD", R.string.rate_aud , R.drawable.ic_aud),
    BGN("BGN", R.string.rate_bgn, R.drawable.ic_bgn_bulgaria),
    BRL("BRL", R.string.rate_brl, R.drawable.ic_brl),
    CAD("CAD", R.string.rate_cad, R.drawable.ic_cad),
    CHF("CHF", R.string.rate_chf, R.drawable.ic_chf_switzerland),
    CNY("CNY", R.string.rate_cny, R.drawable.ic_cny_china),
    CZK("CZK", R.string.rate_czk,R.drawable.ic_czk),
    DKK("DKK", R.string.rate_dkk, R.drawable.ic_dkk),
    GBP("GBP", R.string.rate_gbp, R.drawable.ic_gbp_uk),
    IDR("IDR", R.string.rate_idr, R.drawable.ic_idr),
    ILS("ILS", R.string.rate_ils, R.drawable.ic_ils),
    INR("INR", R.string.rate_inr, R.drawable.ic_inr),
    ISK("ISK", R.string.rate_isk, R.drawable.ic_isk),
    JPY("JPY", R.string.rate_jpy, R.drawable.ic_jpy),
    KRW( "KRW", R.string.rate_krw, R.drawable.ic_krw),
    MXN( "MXN", R.string.rate_mxn, R.drawable.ic_mxn),
    MYR("MYR", R.string.rate_myr, R.drawable.ic_myr),
    NOK("NOK", R.string.rate_nok, R.drawable.ic_nok),
    NZD("NZD", R.string.rate_nzd, R.drawable.ic_nzd),
    PHP("PHP", R.string.rate_php, R.drawable.ic_php),
    PLN("PLN", R.string.rate_pln, R.drawable.ic_pln),
    RON("RON", R.string.rate_ron, R.drawable.ic_ron),
    SEK("SEK", R.string.rate_sek, R.drawable.ic_sek),
    SGD("SGD", R.string.rate_sgd, R.drawable.ic_sgd),
    THB("THB", R.string.rate_thb, R.drawable.ic_thb),
    TRY("TRY", R.string.rate_try, R.drawable.ic_try),
    USD("USD", R.string.rate_usd, R.drawable.ic_usd),
    ZAR("ZAR", R.string.rate_zar, R.drawable.ic_zar),
    HKD("HKD", R.string.rate_hkd, R.drawable.ic_hkd),
    HRK("HRK", R.string.rate_hrk, R.drawable.ic_hrk),
    HUF("HUF", R.string.rate_huf, R.drawable.ic_huf),
    RUB("RUB", R.string.rate_rub, R.drawable.ic_rub),
    EUR("EUR", R.string.rate_eur, R.drawable.ic_eur);

   companion object {
      fun getCurrencyName(code: String) : Int{
         return try {
            CurrencyInfo.values().first {
               it.code == code
            }.currencyName
         }catch (e:Exception){
             R.string.rate_not_found
         }
      }

      fun getFlagId(code: String) : Int{
         return try {
            CurrencyInfo.values().first {
               it.code == code
            }.flag
         }catch (e:Exception){
            R.drawable.shadow
         }
      }

   }

}