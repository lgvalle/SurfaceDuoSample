package xyz.lgvalle.surfaceduosample

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.microsoft.device.display.samples.utils.ScreenHelper
import xyz.lgvalle.surfaceduosample.ui.main.Item
import xyz.lgvalle.surfaceduosample.ui.main.ItemFragmentDetails
import xyz.lgvalle.surfaceduosample.ui.main.ItemFragmentList
import xyz.lgvalle.surfaceduosample.ui.main.ItemViewModel

class MainActivity : AppCompatActivity() {

    private val screenHelper = ScreenHelper()
    private val isDuoDevice by lazy { screenHelper.initialize(this) }
    private val viewModel by lazy { ViewModelProviders.of(this).get(ItemViewModel::class.java) }

    private val dualModeIds = LayoutIdReference(
        layoutId = R.layout.main_activity_dual_mode,
        list = R.id.master_main,
        details = R.id.master_detail
    )
    private val singleModelIds = LayoutIdReference(
        layoutId = R.layout.main_activity_single_mode,
        list = R.id.container,
        details = R.id.container
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLayout()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setupLayout()
    }

    private fun setupLayout() {
        showItemList(getLayoutIds())
        viewModel.selectedItem.observe(this@MainActivity, Observer {
            it?.let { showItemDetails(it) }
        })
    }

    private fun showItemList(ids: LayoutIdReference) {
        setContentView(ids.layoutId)

        supportFragmentManager.beginTransaction()
            .replace(ids.list, ItemFragmentList.newInstance())
            .commitNow()
    }

    private fun showItemDetails(item: Item) {
        val fragmentDetails = ItemFragmentDetails.newInstance(item)

        supportFragmentManager.beginTransaction()
            .add(getLayoutIds().details, fragmentDetails)
            .addToBackStack(fragmentDetails.javaClass.name)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        viewModel.deselectAny()
    }

    private fun getLayoutIds() = when {
        isDuoDevice && screenHelper.isDualMode -> dualModeIds
        else -> singleModelIds
    }

    private data class LayoutIdReference(val layoutId: Int, val list: Int, val details: Int)
}

