package com.dd.githubapp.ui.ui.about

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dd.githubapp.R
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import kotlinx.android.synthetic.main.fragment_about.*
import org.jetbrains.anko.support.v4.toast
import java.lang.IllegalArgumentException

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel

    private lateinit var itemDialog: QMUIDialog;

    private lateinit var bottomSheet: QMUIBottomSheet

    companion object {
        private const val TAG_SHARE_WECHAT = 1
        private const val TAG_SHARE_FRIEND_CIRCLE = 2
        private const val TAG_SHARE_WEIBO = 3
        private const val TAG_SHARE_COPY = 4
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
            ViewModelProviders.of(this).get(AboutViewModel::class.java)
        init()
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        initView(root)
        return root
    }

    private fun init() {
        val items = arrayOf("RxJava2", "RxAndroid", "Gson", "Glide", "qmui")
        itemDialog = QMUIDialog.MenuDialogBuilder(context)
            .addItems(items, null)
            .create()
        bottomSheet = QMUIBottomSheet.BottomGridSheetBuilder(context)
            .addItem(
                R.drawable.ic_share_wechat,
                getString(R.string.about_share_wechat),
                TAG_SHARE_WECHAT,
                QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE
            )
            .addItem(
                R.drawable.ic_share_friend_circle,
                getString(R.string.about_share_friend_circle),
                TAG_SHARE_FRIEND_CIRCLE,
                QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE
            )
            .addItem(
                R.drawable.ic_share_weibo,
                getString(R.string.about_share_weibo),
                TAG_SHARE_WEIBO,
                QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE
            )
            .addItem(
                R.drawable.ic_share_copy,
                getString(R.string.about_share_copy),
                TAG_SHARE_COPY,
                QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE
            )
            .setOnSheetItemClickListener(QMUIBottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener { qmuiBottomSheet: QMUIBottomSheet, view: View ->
                when (view.tag) {
                    TAG_SHARE_WECHAT -> toast(getString(R.string.about_share_wechat))
                    TAG_SHARE_FRIEND_CIRCLE -> toast(getString(R.string.about_share_friend_circle))
                    TAG_SHARE_WEIBO -> toast(getString(R.string.about_share_weibo))
                    TAG_SHARE_COPY -> toast(getString(R.string.about_share_copy))
                    else -> throw IllegalArgumentException("No such tag!!")
                }
                qmuiBottomSheet.dismiss()
            })
            .build()
    }

    private fun initView(root: View) {
        aboutViewModel.about.observe(this, Observer {

            context?.let { ctx ->

                // Author
                var authorItem = lvAbout.createItemView(getString(R.string.about_author))
                authorItem.orientation = QMUICommonListItemView.VERTICAL
                authorItem.detailText = getString(R.string.about_author_link)
                authorItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
                var githubItem = lvAbout.createItemView(getString(R.string.about_open_source))
                githubItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON

                QMUIGroupListView.newSection(ctx)
                    .addItemView(authorItem, View.OnClickListener {
                        toast("待开发~")
                    })
                    .addItemView(githubItem, View.OnClickListener {
                        itemDialog.show()
                    })
                    .addTo(lvAbout)

                // Version
                var versionItem = lvAbout.createItemView(getString(R.string.about_version_code))
                versionItem.detailText =
                    ctx.packageManager.getPackageInfo(ctx.packageName, 0).versionName
                var shareItem = lvAbout.createItemView(getString(R.string.about_share))
                shareItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON

                QMUIGroupListView.newSection(ctx)
                    .setTitle("")
                    .addItemView(versionItem, null)
                    .addItemView(shareItem, View.OnClickListener {
                        bottomSheet.show()
                    })
                    .addTo(lvAbout)


            }

        })

    }

}