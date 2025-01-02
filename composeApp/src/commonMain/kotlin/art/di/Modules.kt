package art.di

import art.core.data.HttpClientFactory
import art.data.network.RemoteArtDataSource
import art.data.network.RemoteArtDataSourceImpl
import art.data.repository.ArtworkRepositoryImpl
import art.domain.ArtworkRepository
import art.presentation.art_details.ArtDetailsViewModel
import art.presentation.art_list.ArtListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::ArtworkRepositoryImpl).bind<ArtworkRepository>()
    singleOf(::RemoteArtDataSourceImpl).bind<RemoteArtDataSource>()

    viewModelOf(::ArtListViewModel)
    viewModelOf(::ArtDetailsViewModel)
}
