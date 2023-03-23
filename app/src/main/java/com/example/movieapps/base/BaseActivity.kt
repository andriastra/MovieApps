package com.example.movieapps.base

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {
    companion object {
        const val EMPTY_TOOLBAR = 0
    }
    /**
     * Variabel for view binding
     */
    lateinit var viewBinding: VDB

    /**
     * Variabel for hold instance app compat activity
     */
    lateinit var mActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this@BaseActivity, bindLayoutRes())
        viewBinding.apply {
            mActivity = this@BaseActivity
            setupToolbar()
            onLoadObserver()
            onSetupView()
            onViewClicked()
        }
    }

    private fun setupToolbar() {
        if (bindToolbarId() != EMPTY_TOOLBAR) {
            setSupportActionBar(findViewById(bindToolbarId()))
            supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)

            }
        }
    }

    protected infix fun Activity.showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }


    /**
     * abstract Method for define variabel observer from viewModel
     *
     * @param void
     * @return void
     */
    abstract fun onLoadObserver()

    /**
     * method call automatic when activity or fragment created
     * @param void
     * @return void
     */
    abstract fun onSetupView()

    /**
     * method call automatic when activity or fragment created
     * @param void
     * @return void
     */
    abstract fun onViewClicked()

    /**
     * Method for get layout rest
     * @param void
     * @return Int
     */
    @LayoutRes
    abstract fun bindLayoutRes(): Int

    /**
     * Method for get toolbarId
     * @param void
     * @return Int
     */
    @IdRes
    abstract fun bindToolbarId(): Int
}