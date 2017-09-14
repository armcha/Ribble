package com.luseen.ribble.presentation.screen.about


import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.luseen.ribble.R
import com.luseen.ribble.presentation.base_mvp.base.BaseFragment
import com.luseen.ribble.presentation.widget.navigation_view.NavigationId
import com.luseen.ribble.utils.extensions.iconTint
import com.luseen.ribble.utils.extensions.leftIcon
import kotlinx.android.synthetic.main.fragment_about.*
import javax.inject.Inject


class AboutFragment : BaseFragment<AboutContract.View, AboutContract.Presenter>(),
        AboutContract.View, View.OnClickListener {

    private val EMAIL = 0
    private val TWITTER = 1
    private val FACEBOOK = 2
    private val GITHUB = 3

    @Inject
    protected lateinit var aboutPresenter: AboutPresenter

    private val items = mutableListOf(
            AboutItem(R.string.email, R.drawable.email, R.color.colorAccent),
            AboutItem(R.string.twitter, R.drawable.twitter, R.color.twitter),
            AboutItem(R.string.facebook, R.drawable.facebook, R.color.facebook),
            AboutItem(R.string.github, R.drawable.github, R.color.github)
    )

    override fun injectDependencies() {
        activityComponent.inject(this)
    }

    override fun layoutResId() = R.layout.fragment_about

    override fun initPresenter() = aboutPresenter

    override fun getTitle(): String {
        return NavigationId.ABOUT.name
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (2 until 9 step 2)
                .map { rootView.getChildAt(it) as TextView }
                .onEach { it.setOnClickListener(this) }
                .forEachIndexed { current, textView ->
                    val aboutItem = items[current]
                    with(textView) {
                        text = getString(aboutItem.stringResId)
                        leftIcon(aboutItem.icon)
                        iconTint(aboutItem.tintColor)
                        id = current
                    }
                }
    }

    override fun onClick(view: View) {
        when (view.id) {
            EMAIL -> {

            }
            TWITTER -> {

            }
            FACEBOOK -> {

            }
            GITHUB -> {

            }
        }
    }
}

data class AboutItem(val stringResId: Int, val icon: Int, val tintColor: Int = 0)
