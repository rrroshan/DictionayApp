package com.example.dictionayapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.dictionayapp.fragments.FragmentAntonyms;
import com.example.dictionayapp.fragments.FragmentDefination;
import com.example.dictionayapp.fragments.FragmentExample;
import com.example.dictionayapp.fragments.FragmentSynonyms;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class WordMeaningActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
     private ViewPagerAdapter viewPagerAdapter;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_meaning);

        Toolbar toolbar = findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("English Words");

        toolbar.setNavigationIcon(R.drawable.ic_back_24dp);
        viewPager =  findViewById(R.id.tab_viewpager);

        viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);
TabLayout tabLayout = findViewById(R.id.tabLayout);


            TabLayoutMediator tabLayoutMediator= new TabLayoutMediator(
             tabLayout,viewPager, (TabLayout.Tab tab, int position) -> {
                 switch (position){
                     case 0:
                     {
                         tab.setText("Defination");
                         break;
                     }
                     case 1: {
                         tab.setText("Example");
                         break;
                     }

                     case 2: {
                         tab.setText("Antonynms");
                         break;
                     }
                     case 3: {
                         tab.setText("Synonyms");
                     }

                 }



             }


            );
            tabLayoutMediator.attach();

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
    }



    private class ViewPagerAdapter extends FragmentStateAdapter {


        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new FragmentDefination();


                case 1:
                    return new FragmentExample();

                case 2:
                    return new FragmentAntonyms();
                case 3:
                    return new FragmentSynonyms();
                default:
                    return new FragmentDefination();
            }
        }

        @Override
        public int getItemCount() {
            return 4;
        }

        // @Nullable
        // @Override
        //public CharSequence getPageTitle(int position) {
        //   return mFragmentTitleList.get(position);
        //}


        //   private void setupViewPager(ViewPager2 viewPager) {

        //     ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //   adapter.addFrag(new FragmentDefination(), "Defination");

        // adapter.addFrag(new FragmentSynonyms(), "Synonyms");

        //adapter.addFrag(new FragmentAntonyms(), "Antonyms");

        //adapter.addFrag(new FragmentExample(), "Example");
        // viewPager.setAdapter(adapter);
        //  }

    }
        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == android.R.id.home)           //press back Icon
            {
                onBackPressed();
            }

            return super.onOptionsItemSelected(item);


    }
    }
