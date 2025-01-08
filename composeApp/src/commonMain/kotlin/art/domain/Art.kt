package art.domain

data class Art(
    val pagination: Pagination,
    val art: List<ArtItem>
)

data class Pagination(
    val currentPage: Int
)

data class ArtItem(
    val id: Long?,
    val apiModel: String?,
    val apiLink: String?,
    val isBoosted: Boolean?,
    val title: String?,
    val altTitles: List<String>?, // check
    val mainReferenceNumber: String?,
    val hasNotBeenViewedMuch: Boolean?,
    val boostRank: Int?,
    val dateStart: Long?,
    val dateEnd: Long?,
    val dateDisplay: String?,
    val dateQualifierTitle: String?,
    val dateQualifierId: Int?, // check
    val artistDisplay: String?,
    val placeOfOrigin: String?,
    val description: String?,
    val shortDescription: String?,
    val dimensions: String?,
    val mediumDisplay: String?,
    val inscriptions: String?, // check
    val creditLine: String?,
    val catalogueDisplay: String?, // check
    val publicationHistory: String?,
    val exhibitionHistory: String?,
    val provenanceText: String?,
  //  val edition: List<String>?, // check
    val publishingVerificationLevel: String?,
    val internalDepartmentId: Long?,
    val fiscalYear: Long?,
    val fiscalYearDeaccession: String?, //check
    val isPublicDomain: Boolean?,
    val isZoomable: Boolean?,
    val maxZoomWindowSize: Long?,
    val copyrightNotice: String?, // check
    val hasMultimediaResources: Boolean?,
    val hasEducationalResources: Boolean?,
    val hasAdvancedImaging: Boolean?,
    val colorfulness: Double?,
    val isOnView: Boolean?,
    val onLoanDisplay: String?, // check
    val galleryTitle: String?,
    val galleryId: Long?,
    val nomismaId: String?, // check
    val artworkTypeTitle: String?,
    val artworkTypeId: Long?,
    val departmentTitle: String?,
    val departmentId: String?,
    val artistId: Long?,
    val artistTitle: String?,
    val altArtistIds: List<Int>?, // check
    val artistIds: List<Long>?,
    val artistTitles: List<String>?,
    val termTitles: List<String>?,
    val styleId: String?,
    val styleTitle: String?,
    val styleIds: List<String>?,
    val styleTitles: List<String>?,
    val classificationId: String?,
    val classificationTitle: String?,
    val subjectId: String?,
    val altSubjectIds: List<String>?,
    val subjectIds: List<String>?,
    val subjectTitles: List<String>?,
    val altStyleIds: List<String>?, // check
    val materialId: String?,
    val altMaterialIds: List<String>?,
    val materialIds: List<String>?,
    val materialTitles: List<String>?,
    val techniqueId: String?,
    val altTechniqueIds: List<String>?, // check
    val techniqueIds: List<String>?,
    val techniqueTitles: List<String>?,
    val themeTitles: List<String>?,
    val imageId: String?,
    val altImageIds: List<String>?, // check
    val documentIds: List<String>?,
    val soundIds: List<String>?, // check
    val videoIds: List<String>?, // check
    val textIds: List<String>?,
  //  val sectionIds: List<Int>?, // check
    val sectionTitles: List<String>?, // check
    val siteIds: List<String>?, // check
    val sourceUpdatedAt: String?,
    val updatedAt: String?,
    val timestamp: String?
)
