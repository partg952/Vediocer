package com.example.vediocer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class adapter(fm:FragmentManager):FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
       return when(position){
            0->{
                first()
            }
            1->{
                second()
            }
            else->{
                first()
            }
        }
    }
    
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->{
                "Videos"
            }
            1->{
                "+"
            }
            else->{
                "Videos"
            }
        }
        return super.getPageTitle(position)
    }

}