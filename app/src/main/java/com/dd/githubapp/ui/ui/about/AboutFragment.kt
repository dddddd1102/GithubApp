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
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import kotlinx.android.synthetic.main.fragment_about.*
import org.jetbrains.anko.support.v4.toast

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel

    private lateinit var itemDialog: QMUIDialog;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
            ViewModelProviders.of(this).get(AboutViewModel::class.java)
        val items = arrayOf("RxJava2", "RxAndroid", "Gson", "Glide", "qmui")
        itemDialog = QMUIDialog.MenuDialogBuilder(context)
            .addItems(items, null)
            .create()
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        initView(root)
        return root
    }

    private fun initView(root: View) {
        aboutViewModel.about.observe(this, Observer {

            context?.let { ctx ->

                // Author
                var authorItem = lvAbout.createItemView("作者")
                authorItem.orientation = QMUICommonListItemView.VERTICAL
                authorItem.detailText = "dddddd1102"
                authorItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
                var githubItem = lvAbout.createItemView("开源库")
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
                var versionItem = lvAbout.createItemView("版本号")
                versionItem.detailText =
                    ctx.packageManager.getPackageInfo(ctx.packageName, 0).versionName

                QMUIGroupListView.newSection(ctx)
                    .setTitle("")
                    .addItemView(versionItem, null)
                    .addTo(lvAbout)

            }

        })

    }

}