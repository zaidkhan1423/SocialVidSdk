package com.zaid.socialvidsdk.feature_home.presentation.home_screen

import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.zaid.socialvidsdk.feature_home.data.model.response.SocialVidResponse
import com.zaid.socialvidsdk.feature_home.domain.HomeRepository
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.composable.ComposableLifecycle
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.composable.PlayerListener
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.effect.AnimationEffect
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.effect.PlayerErrorEffect
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.effect.ResetAnimationEffect
import com.zaid.socialvidsdk.feature_home.presentation.home_screen.state.HomeUiState
import com.zaid.socialvidsdk.utils.AppIcons
import com.zaid.socialvidsdk.utils.findActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    viewModel: HomeScreenViewModel,
    onMessageDisplay: () -> Unit,
    onShowSnackBar: suspend (message: String, actionLabel: String?, duration: SnackbarDuration) -> Boolean
) {
    LaunchedEffect(key1 = homeUiState) {
        if (homeUiState.snackBarMessage != null) {
            onShowSnackBar(homeUiState.snackBarMessage, null, SnackbarDuration.Short)
            onMessageDisplay()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {

        if (homeUiState.socialVidResponse.msg[0].video == "") {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.background,
                strokeWidth = 4.dp
            )
        } else {
            VideoPager(
                state = homeUiState,
                viewModel = viewModel
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 20.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Following | ", color = Color.White)
            Text(text = "For You", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun VideoPager(
    state: HomeUiState,
    viewModel: HomeScreenViewModel
) {
    val pagerState = com.google.accompanist.pager.rememberPagerState()

    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.distinctUntilChanged().collect { page ->
            pagerState.animateScrollToPage(page)
        }
    }

    com.google.accompanist.pager.VerticalPager(
        state = pagerState,
        horizontalAlignment = Alignment.CenterHorizontally,
        count = state.socialVidResponse.msg.map { it.video }.size
    ) { index ->
        if (index == pagerState.currentPage) {
            state.playMediaAt(index)
            VideoCard(
                state = state,
                viewModel = viewModel,
                videoThumbnail = state.socialVidResponse.msg[index].thum
            )
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                        .width(300.dp)
                ) {
                    Text(
                        text = state.socialVidResponse.msg[index].user_info.first_name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                    Text(
                        text = state.socialVidResponse.msg[index].description,
                        fontSize = 13.sp,
                        lineHeight = 15.sp,
                        color = Color.White
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = AppIcons.MusicIcon),
                            contentDescription = null
                        )
                        Text(
                            text = " ${state.socialVidResponse.msg[index].sound.sound_name}",
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 40.dp, end = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(32.dp),
                        model = "https://static.vecteezy.com/system/resources/thumbnails/020/765/399/small/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg",
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = AppIcons.HeartIcon),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                    Text(
                        text = state.socialVidResponse.msg[index].count.like_count.toString(),
                        fontSize = 12.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = AppIcons.CommentIcon),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                    Text(
                        text = state.socialVidResponse.msg[index].count.video_comment_count.toString(),
                        fontSize = 12.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = AppIcons.ShareIcon),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                    Text(
                        text = "Share", fontSize = 12.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Image(
                        painter = painterResource(id = AppIcons.MenuIcon),
                        contentDescription = null,
                    )
                }

            }
        } else {
            Box {
                VideoThumbnail(videoThumbnail = state.socialVidResponse.msg[index].thum)
            }
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                        .width(300.dp)
                ) {
                    Text(
                        text = state.socialVidResponse.msg[index].user_info.first_name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                    Text(
                        text = state.socialVidResponse.msg[index].description,
                        fontSize = 13.sp,
                        lineHeight = 15.sp,
                        color = Color.White
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = AppIcons.MusicIcon),
                            contentDescription = null
                        )
                        Text(
                            text = " ${state.socialVidResponse.msg[index].sound.sound_name}",
                            fontSize = 13.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 40.dp, end = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(32.dp),
                        model = "https://static.vecteezy.com/system/resources/thumbnails/020/765/399/small/default-profile-account-unknown-icon-black-silhouette-free-vector.jpg",
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = AppIcons.HeartIcon),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                    Text(
                        text = state.socialVidResponse.msg[index].count.like_count.toString(),
                        fontSize = 12.sp,
                        color = Color.White

                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = AppIcons.CommentIcon),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                    Text(
                        text = state.socialVidResponse.msg[index].count.video_comment_count.toString(),
                        fontSize = 12.sp,
                        color = Color.White

                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = AppIcons.ShareIcon),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                    Text(
                        text = "Share", fontSize = 12.sp, color = Color.White
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Image(
                        painter = painterResource(id = AppIcons.MenuIcon), contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun BoxScope.VideoThumbnail(
    videoThumbnail: String
) {
    AsyncImage(
        model = videoThumbnail,
        contentDescription = "Preview",
        modifier = Modifier
            .align(Alignment.Center)
            .fillMaxSize()
    )
}

@Composable
fun VideoCard(
    state: HomeUiState,
    viewModel: HomeScreenViewModel,
    videoThumbnail: String
) {
    val context = LocalContext.current
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> viewModel.createPlayer(context)
            Lifecycle.Event.ON_STOP -> viewModel.releasePlayer(context.findActivity()?.isChangingConfigurations == true)
            else -> {}
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var showPlayer by remember { mutableStateOf(false) }
        if (state.player != null) {
            PlayerListener(
                player = state.player
            ) { event ->
                when (event) {
                    Player.EVENT_RENDERED_FIRST_FRAME -> {
                        showPlayer = true
                    }

                    Player.EVENT_PLAYER_ERROR -> {
                        viewModel.onPlayerError()
                    }
                }
            }
            val playerView = rememberPlayerView(state.player)
            Player(
                playerView = playerView,
                viewModel = viewModel
            )
        }
        if (!showPlayer) {
            VideoThumbnail(videoThumbnail = videoThumbnail)
        }
    }
}

@Composable
fun Player(
    playerView: PlayerView,
    viewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier
) {
    var animatedIconDrawable by remember {
        mutableStateOf(0)
    }
    val iconVisibleState = remember {
        MutableTransitionState(false)
    }
    var animationJob: Job? by remember {
        mutableStateOf(null)
    }
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        viewModel.onTappedScreen()
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { playerView },
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        )
        AnimatedVisibility(
            visibleState = iconVisibleState,
            enter = scaleIn(
                spring(Spring.DampingRatioMediumBouncy)
            ),
            exit = scaleOut(tween(150)),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(animatedIconDrawable),
                contentDescription = null,
                tint = Color.White.copy(0.90f),
                modifier = Modifier
                    .size(100.dp)
            )
        }
    }
    LaunchedEffect(key1 = true) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is PlayerErrorEffect -> {
                    val message =
                        if (effect.code == PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED || effect.code == PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT)
                            "Please check your internet connection"
                        else
                            "An error occurred. Code: ${effect.code}"
                    Toast.makeText(context,message, Toast.LENGTH_LONG).show()
                }

                is AnimationEffect -> {
                    animatedIconDrawable = effect.drawable
                    animationJob = launch {
                        iconVisibleState.targetState = true
                        delay(800)
                        iconVisibleState.targetState = false
                    }
                }

                is ResetAnimationEffect -> {
                    iconVisibleState.targetState = false
                    animationJob?.cancel()
                }
            }
        }
    }
}

@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun rememberPlayerView(player: Player): PlayerView {
    val context = LocalContext.current
    val playerView = remember {
        PlayerView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            useController = false
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
            setShowBuffering(PlayerView.SHOW_BUFFERING_NEVER)
            this.player = player
        }
    }
    DisposableEffect(key1 = player) {
        playerView.player = player
        onDispose {
            playerView.player = null
        }
    }
    return playerView
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val homeRepository = FakeRepo()
    HomeScreen(
        homeUiState = HomeUiState(),
        onMessageDisplay = {},
        onShowSnackBar = { _, _, _ -> false },
        viewModel = HomeScreenViewModel(homeRepository)
    )
}

class FakeRepo : HomeRepository {
    override suspend fun getAllVideos(): SocialVidResponse {
        TODO("Not yet implemented")
    }
}