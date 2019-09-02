package com.dd.githubapp.ui.ui.tools

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dd.githubapp.R
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import kotlinx.android.synthetic.main.fragment_tools.*
import org.jetbrains.anko.support.v4.toast

class ToolsFragment : Fragment() {

    private lateinit var toolsViewModel: ToolsViewModel

    private lateinit var languageBuilder: QMUIDialog.CheckableDialogBuilder

    private lateinit var themeBuilder: QMUIDialog.CheckableDialogBuilder

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsViewModel =
            ViewModelProviders.of(this).get(ToolsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tools, container, false)
        initBuilder()
        initView()
        return root
    }

    private fun initBuilder() {
        val languageArray = arrayOf("中文", "English")
        languageBuilder = QMUIDialog.CheckableDialogBuilder(context)
        languageBuilder.setTitle(getString(R.string.tools_language_title))
            .setCheckedIndex(0)
            .addItems(
                languageArray
            ) { _: DialogInterface, which: Int ->
                languageBuilder.checkedIndex = which
            }.addAction(
                getString(R.string.tools_sure)
            ) { dialog: QMUIDialog, index: Int ->
                toast("选择了${languageArray[index]}")
                dialog.dismiss()
            }

        val themeArray = arrayOf("主题1", "主题2", "主题3")
        themeBuilder = QMUIDialog.CheckableDialogBuilder(context)
        themeBuilder.setTitle(getString(R.string.tools_theme_title))
            .setCheckedIndex(0)
            .addItems(
                themeArray
            ) { _: DialogInterface, which: Int ->
                themeBuilder.checkedIndex = which
            }.addAction(getString(R.string.tools_sure)) { dialog: QMUIDialog, index: Int ->
                toast("选择了${themeArray[index]}")
                dialog.dismiss()
            }
    }

    private fun initView() {
        toolsViewModel.tools.observe(this, Observer {
            context?.let { ctx ->
                var themeItem = lvTools.createItemView(getString(R.string.tools_theme))
                themeItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
                themeItem.detailText = "主题1"

                var netItem = lvTools.createItemView("禁用网络图片加载")
                netItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_SWITCH
                netItem.detailText = "在移动网络下禁用网络图片加载"
                netItem.orientation = QMUICommonListItemView.VERTICAL

                var languageItem = lvTools.createItemView(getString(R.string.tools_language))
                languageItem.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
                languageItem.detailText = getString(R.string.tools_language_zh)


                QMUIGroupListView.newSection(ctx)
                    .addItemView(themeItem) {
                        themeBuilder.show()
                    }
                    .addItemView(netItem) {


                    }
                    .addItemView(languageItem) {
                        languageBuilder.show()
                    }
                    .addTo(lvTools)
            }
        })
    }
}