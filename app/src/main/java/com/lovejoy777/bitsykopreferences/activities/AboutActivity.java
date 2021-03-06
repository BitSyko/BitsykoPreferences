package com.lovejoy777.bitsykopreferences.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.lovejoy777.bitsykopreferences.R;
import com.lovejoy777.bitsykopreferences.adapters.AboutAdapter;
import com.lovejoy777.bitsykopreferences.beans.DeveloperBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * Created by lovejoy777 on 10/08/15.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        DeveloperBean[] developers = {
                new DeveloperBean("Syko Pompos", getString(R.string.LayersLeadDeveloper), getDrawable(R.drawable.about_syko), getString(R.string.linkSyko)),
                new DeveloperBean("Reinhard Strauch", getString(R.string.LayersLeadDeveloper), getDrawable(R.drawable.about_reinhard), getString(R.string.linkReinhard)),
                new DeveloperBean("Brian Gill", getString(R.string.LayersDeveloper), getDrawable(R.drawable.about_brian), getString(R.string.linkBrian)),
                new DeveloperBean("Aldrin Holmes", getString(R.string.LayersDeveloper), getDrawable(R.drawable.about_aldrin), getString(R.string.linkAldrin)),
                new DeveloperBean("Branden Manibusan", getString(R.string.LayersDeveloper), getDrawable(R.drawable.about_branden), getString(R.string.linkBranden)),
                new DeveloperBean("Steve Lovejoy", getString(R.string.AppDeveloper), getDrawable(R.drawable.about_steve), getString(R.string.linkSteve)),
                new DeveloperBean("Niklas Schnettler", getString(R.string.AppDeveloper), getDrawable(R.drawable.about_niklas), getString(R.string.linkNiklas)),
                new DeveloperBean("Andrzej Ressel", getString(R.string.AppDeveloper), getDrawable(R.drawable.about_andrzej), getString(R.string.linkAndrzej)),
                new DeveloperBean("Denis Suarez", getString(R.string.ShowcaseDeveloper), getDrawable(R.drawable.about_denis), getString(R.string.linkDenis)),
        };

        DeveloperBean[] usefulLinks = {
                new DeveloperBean("Layers on Google Plus", getString(R.string.findOutWhatsNew), getDrawable(R.drawable.about_bitsyko), getString(R.string.linkCommunity)),
                new DeveloperBean("Layers on XDA", getString(R.string.joinTheChat), getDrawable(R.drawable.about_xda), getString(R.string.linkXda))
        };

        //set Toolbar
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar4);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(toolbar);

        TextView tv_version = (TextView) findViewById(R.id.tv_Version);
        try {
            String versionName = AboutActivity.this.getPackageManager()
                    .getPackageInfo(AboutActivity.this.getPackageName(), 0).versionName;
            tv_version.setText(getResources().getString(R.string.version) + " " + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        ExpandableListView devlist = (ExpandableListView) findViewById(R.id.developers);

        final HashMap<String, List<DeveloperBean>> listDataChild = new HashMap<>();

        final ArrayList<String> listDataHeader = new ArrayList<>();
        listDataHeader.add(getResources().getString(R.string.developedby));
        listDataHeader.add(getResources().getString(R.string.usefullinks));

        listDataChild.put(listDataHeader.get(0), Arrays.asList(developers));
        listDataChild.put(listDataHeader.get(1), Arrays.asList(usefulLinks));

        devlist.setGroupIndicator(null);
        devlist.setAdapter(new AboutAdapter(this, listDataHeader, listDataChild));

        for (int i = 0; i < devlist.getExpandableListAdapter().getGroupCount(); i++) {
            devlist.expandGroup(i);
        }

        devlist.setDividerHeight(26);
        devlist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (groupPosition <= 1) {
                    String url = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).getWebpage();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } else {
                    throw new IllegalArgumentException();
                }

                return true;
            }
        });

        devlist.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.back2, R.anim.back1);
    }
}