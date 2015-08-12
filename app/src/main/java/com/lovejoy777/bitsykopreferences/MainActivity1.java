package com.lovejoy777.bitsykopreferences;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lovejoy777.bitsykopreferences.adapters.CardAdapter;
import com.lovejoy777.bitsykopreferences.adapters.RecyclerItemClickListener;

/**
 * Created by lovejoy777 on 10/08/15.
 */
public class MainActivity1 extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        // Handle Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(toolbar);

        //set NavigationDrawer
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity1.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        if (position == 0) {
                            boolean installedManager = appInstalledOrNot("com.lovejoy777.rroandlayersmanager");
                            if (installedManager) {
                                Intent intent = new Intent();
                                intent.setComponent(new ComponentName("com.lovejoy777.rroandlayersmanager", "com.lovejoy777.rroandlayersmanager.menu"));
                                Bundle bndlanimation =
                                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
                                startActivity(intent, bndlanimation);
                            } else {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.lovejoy777.rroandlayersmanager&hl=en")));
                            }
                        }

                        if (position == 1) {
                            boolean installedShowcase = appInstalledOrNot("com.lovejoy777.showcase");
                            if (installedShowcase) {
                                Intent intent = new Intent();
                                intent.setComponent(new ComponentName("com.lovejoy777.showcase", "com.lovejoy777.showcase.MainActivity1"));
                                Bundle bndlanimation =
                                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
                                startActivity(intent, bndlanimation);
                            } else {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/apps/testing/com.lovejoy777.showcase")));
                            }
                        }

                        if (position == 2) {
                            boolean installedRommate = appInstalledOrNot("com.lovejoy777.rommate");
                            if (installedRommate) {
                                Intent intent = new Intent();
                                intent.setComponent(new ComponentName("com.lovejoy777.rommate", "com.lovejoy777.rommate.MainActivity1"));
                                Bundle bndlanimation =
                                        ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
                                startActivity(intent, bndlanimation);
                            } else {
                                Toast.makeText(getApplicationContext(), "RomMate is not installed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
        );
    }
    //set NavigationDrawerContent
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        mDrawerLayout.closeDrawers();
                        menuItem.setChecked(true);
                        Bundle bndlanimation =
                                ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.anni1, R.anim.anni2).toBundle();
                        int id = menuItem.getItemId();
                        switch (id) {
                            case R.id.nav_home:
                                mDrawerLayout.closeDrawers();
                                break;
                            case R.id.nav_about:
                                Intent about = new Intent(MainActivity1.this, com.lovejoy777.bitsykopreferences.activities.AboutActivity.class);
                                startActivity(about, bndlanimation);
                                break;
                            case R.id.nav_settings:
                                Intent settings = new Intent(MainActivity1.this, Settings.class);
                                startActivity(settings, bndlanimation);
                                break;
                        }
                        return false;
                    }
                });
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back2, R.anim.back1);
    }
}