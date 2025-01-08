package art.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtDto(
    val data: ArtDataDto
)

@Serializable
data class ArtDataDto(
    val id: Long?,
    @SerialName("api_model")
    val apiModel: String?,
    @SerialName("api_link")
    val apiLink: String?,
    @SerialName("is_boosted")
    val isBoosted: Boolean?,
    val title: String?,
    @SerialName("alt_titles")
    val altTitles: List<String>?, // check
    @SerialName("main_reference_number")
    val mainReferenceNumber: String?,
    @SerialName("has_not_been_viewed_much")
    val hasNotBeenViewedMuch: Boolean?,
    @SerialName("boost_rank")
    val boostRank: Int?,
    @SerialName("date_start")
    val dateStart: Long?,
    @SerialName("date_end")
    val dateEnd: Long?,
    @SerialName("date_display")
    val dateDisplay: String?,
    @SerialName("date_qualifier_title")
    val dateQualifierTitle: String?,
    @SerialName("date_qualifier_id")
    val dateQualifierId: Int?, // check
    @SerialName("artist_display")
    val artistDisplay: String?,
    @SerialName("place_of_origin")
    val placeOfOrigin: String?,
    val description: String?,
    @SerialName("short_description")
    val shortDescription: String?,
    val dimensions: String?,
    @SerialName("medium_display")
    val mediumDisplay: String?,
    val inscriptions: String?, // check
    @SerialName("credit_line")
    val creditLine: String?,
    @SerialName("catalogue_display")
    val catalogueDisplay: String?, // check
    @SerialName("publication_history")
    val publicationHistory: String?,
    @SerialName("exhibition_history")
    val exhibitionHistory: String?,
    @SerialName("provenance_text")
    val provenanceText: String?,
   // val edition: List<String>?, // check
    @SerialName("publishing_verification_level")
    val publishingVerificationLevel: String?,
    @SerialName("internal_department_id")
    val internalDepartmentId: Long?,
    @SerialName("fiscal_year")
    val fiscalYear: Long?,
    @SerialName("fiscal_year_deaccession")
    val fiscalYearDeaccession: String?, //check
    @SerialName("is_public_domain")
    val isPublicDomain: Boolean?,
    @SerialName("is_zoomable")
    val isZoomable: Boolean?,
    @SerialName("max_zoom_window_size")
    val maxZoomWindowSize: Long?,
    @SerialName("copyright_notice")
    val copyrightNotice: String?, // check
    @SerialName("has_multimedia_resources")
    val hasMultimediaResources: Boolean?,
    @SerialName("has_educational_resources")
    val hasEducationalResources: Boolean?,
    @SerialName("has_advanced_imaging")
    val hasAdvancedImaging: Boolean?,
    val colorfulness: Double?,
    @SerialName("is_on_view")
    val isOnView: Boolean?,
    @SerialName("on_loan_display")
    val onLoanDisplay: String?, // check
    @SerialName("gallery_title")
    val galleryTitle: String?,
    @SerialName("gallery_id")
    val galleryId: Long?,
    @SerialName("nomisma_id")
    val nomismaId: String?, // check
    @SerialName("artwork_type_title")
    val artworkTypeTitle: String,
    @SerialName("artwork_type_id")
    val artworkTypeId: Long?,
    @SerialName("department_title")
    val departmentTitle: String?,
    @SerialName("department_id")
    val departmentId: String?,
    @SerialName("artist_id")
    val artistId: Long?,
    @SerialName("artist_title")
    val artistTitle: String?,
    @SerialName("alt_artist_ids")
    val altArtistIds: List<Int>?, // check
    @SerialName("artist_ids")
    val artistIds: List<Long>?,
    @SerialName("artist_titles")
    val artistTitles: List<String>?,
    @SerialName("term_titles")
    val termTitles: List<String>?,
    @SerialName("style_id")
    val styleId: String?,
    @SerialName("style_title")
    val styleTitle: String?,
    @SerialName("alt_style_ids")
    val altStyleIds: List<String>?, // check
    @SerialName("style_ids")
    val styleIds: List<String>?,
    @SerialName("style_titles")
    val styleTitles: List<String>?,
    @SerialName("classification_id")
    val classificationId: String?,
    @SerialName("classification_title")
    val classificationTitle: String?,
    @SerialName("subject_id")
    val subjectId: String?,
    @SerialName("alt_subject_ids")
    val altSubjectIds: List<String>?,
    @SerialName("subject_ids")
    val subjectIds: List<String>?,
    @SerialName("subject_titles")
    val subjectTitles: List<String>?,
    @SerialName("material_id")
    val materialId: String?,
    @SerialName("alt_material_ids")
    val altMaterialIds: List<String>?,
    @SerialName("material_ids")
    val materialIds: List<String>?,
    @SerialName("material_titles")
    val materialTitles: List<String>?,
    @SerialName("technique_id")
    val techniqueId: String?,
    @SerialName("alt_technique_ids")
    val altTechniqueIds: List<String>?, // check
    @SerialName("technique_ids")
    val techniqueIds: List<String>?,
    @SerialName("technique_titles")
    val techniqueTitles: List<String>?,
    @SerialName("theme_titles")
    val themeTitles: List<String>?,
    @SerialName("image_id")
    val imageId: String?,
    @SerialName("alt_image_ids")
    val altImageIds: List<String>?, // check
    @SerialName("document_ids")
    val documentIds: List<String>?,
    @SerialName("sound_ids")
    val soundIds: List<String>?, // check
    @SerialName("video_ids")
    val videoIds: List<String>?, // check
    @SerialName("text_ids")
    val textIds: List<String>?,
  /*  @SerialName("section_ids")
    val sectionIds: List<Int>?, // check
  */
    @SerialName("section_titles")
    val sectionTitles: List<String>?, // check
    @SerialName("site_ids")
    val siteIds: List<String>?, // check
    @SerialName("source_updated_at")
    val sourceUpdatedAt: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    val timestamp: String?
)
