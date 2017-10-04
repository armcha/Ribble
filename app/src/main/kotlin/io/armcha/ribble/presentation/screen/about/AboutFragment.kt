package io.armcha.ribble.presentation.screen.about


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import io.armcha.ribble.R
import io.armcha.ribble.presentation.base_mvp.base.BaseFragment
import io.armcha.ribble.presentation.utils.D
import io.armcha.ribble.presentation.utils.L
import io.armcha.ribble.presentation.utils.S
import io.armcha.ribble.presentation.utils.extensions.actionView
import io.armcha.ribble.presentation.utils.extensions.leftIcon
import io.armcha.ribble.presentation.utils.extensions.sendEmail
import io.armcha.ribble.presentation.utils.extensions.text
import io.armcha.ribble.presentation.widget.navigation_view.NavigationId
import kotlinx.android.synthetic.main.fragment_about.*
import javax.inject.Inject


class AboutFragment : BaseFragment<AboutContract.View, AboutContract.Presenter>(),
        AboutContract.View, View.OnClickListener {

    private val EMAIL = 0
    private val TWITTER = 1
    private val FACEBOOK = 2
    private val GITHUB = 3
    private val INFO = 4

    @Inject
    protected lateinit var aboutPresenter: AboutPresenter

    private val items = mutableListOf(
            S.email to R.drawable.email,
            S.twitter to R.drawable.twitter,
            S.facebook to R.drawable.facebook,
            S.github to R.drawable.github)

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun layoutResId() = L.fragment_about

    override fun initPresenter() = aboutPresenter

    override fun getTitle() = NavigationId.ABOUT.name

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (3 until 10 step 2)
                .map { rootView.getChildAt(it) as TextView }
                .onEach { it.setOnClickListener(this) }
                .forEachIndexed { current, textView ->
                    val (stringRes, icon) = items[current]
                    with(textView) {
                        text = getString(stringRes)
                        leftIcon(icon)
                        id = current
                    }
                }
        with(appInfo) {
            leftIcon(D.about)
            text = """${getString(S.app_name)} ${io.armcha.ribble.BuildConfig.VERSION_NAME}
            |Copyright ${Typography.copyright} 2014-2017
            |Arman Chatikyan""".trimMargin()
            id = INFO
            setOnClickListener(this@AboutFragment)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            EMAIL -> context.sendEmail(getString(S.app_name), getString(S.mail), getString(S.sen_us_emial))
            TWITTER -> actionView { S.twitter_url }
            FACEBOOK -> actionView { S.facebook_url }
            GITHUB -> actionView { S.githun_url }
            INFO -> showDialog(S.easter_title.text(), S.easter_message.text(), S.easter_button_text.text())
        }
    }

    private inline fun actionView(action: () -> Int) {
        context.actionView { getString(action()) }
    }
}