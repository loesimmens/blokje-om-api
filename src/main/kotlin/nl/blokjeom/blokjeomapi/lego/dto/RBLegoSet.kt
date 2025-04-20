package nl.blokjeom.blokjeomapi.lego.dto

data class RBLegoSet(
    val set_num: String,
    val name: String,
    val year: Int,
    val theme_id: Int,
    val num_parts: Int,
    val set_img_url: String,
    val last_modified_dt: String,
)