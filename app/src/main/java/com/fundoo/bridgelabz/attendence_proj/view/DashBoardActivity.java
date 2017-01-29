package com.fundoo.bridgelabz.attendence_proj.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fundoo.bridgelabz.attendence_proj.Interface.IDashBoard;
import com.fundoo.bridgelabz.attendence_proj.R;
import com.fundoo.bridgelabz.attendence_proj.model.DashBoardModel;
import com.fundoo.bridgelabz.attendence_proj.viewmodel.DashBoardViewModel;
import com.loopj.android.http.RequestParams;

import java.util.Calendar;
import java.util.Locale;
/**
 * Created by bridgelabz on 15/12/16.
 * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 *
 * Displays The RecyclerView Containing All ContentList.It is The MainActivity
 * Which Need To Be Included In Manifest.xml File.
 *
 */
public class DashBoardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IDashBoard {
    ProgressDialog progressDialog;
    public DashBoardActivity() {
    }

    private static final String TAG = "DashBoardActivity";

    Toolbar mToolbar;
    private  Context mContext;
    Button unmarkCardButton, markCardButton;
    CardView mAttendanceCardviw, mFalloutCardView, mLeaveSummaryCardView;
    // String[] monthNames={""}
    TextView txt_card_attend_date, fallcardviewdate, leavedateTv, attendDateTV, faloutempTv, totalEmpTv, leaveEmpTv;
    String[]   monthNames = new String[]{"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    String mUnmark, leavecard;
    int mark, totalfalloutemp, totalemp;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    Bundle bundle;
    boolean isListenerActive = false;



    public DashBoardActivity(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navg__attendence);
        unmarkCardButton = (Button) findViewById(R.id.btn_unmarked);
        markCardButton = (Button) findViewById(R.id.btn_marked);
        faloutempTv = (TextView) findViewById(R.id.fall_numb);
        totalEmpTv = (TextView) findViewById(R.id.fall_text_out);
        leaveEmpTv = (TextView) findViewById(R.id.leave_numb);
//putting progress dailog
        progressDialog= new ProgressDialog(DashBoardActivity.this);
        //
        SharedPreferences preferences = this.getSharedPreferences("RECORDS", Context.MODE_PRIVATE);
        String tokenHeader = preferences.getString("token", null);
        Log.i("newdashboard: ", "newtoken" + tokenHeader);
        Long tsLong = System.currentTimeMillis();
        String tsmillisec = tsLong.toString();
        String dashboard=getResources().getString(R.string.Dasboardcontrller);
        //      sending token and timestamp from view to viewmodel
        RequestParams params = new RequestParams();
       // params.put("token", tokendata5);
        params.put("timeStamp", tsmillisec);
        DashBoardViewModel viewModel = new DashBoardViewModel();
        viewModel.getRequest(dashboard,params,tokenHeader, this);
        Log.i("hi", "onCreate: " + params);

        //get current month and year
        Calendar _calendar = Calendar.getInstance(Locale.getDefault());
        int m_Month = _calendar.get(Calendar.MONTH) + 1;
        int mYear = _calendar.get(Calendar.YEAR);
        int mDate = _calendar.get(Calendar.DATE);

        //card textview date
        txt_card_attend_date = (TextView) findViewById(R.id.attend_date);
        txt_card_attend_date.setText((mDate) + " " + monthNames[m_Month - 1] + " " + mYear);
        //setting date on cardview
        fallcardviewdate = (TextView) findViewById(R.id.fallcard_date);
        fallcardviewdate.setText( " " + monthNames[m_Month - 1] + " " + mYear);
        //setting date on cardview
        leavedateTv = (TextView) findViewById(R.id.leavecard_date);
        leavedateTv.setText((mDate - 1) + " " + monthNames[m_Month - 1] + " " + mYear);
        //setting date on Attendance cardview
        attendDateTV = (TextView) findViewById(R.id.txt_card_attend_date);
        attendDateTV.setText((mDate - 1) + " " + monthNames[m_Month - 1] + " " + mYear);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.toolcolor));

        mFalloutCardView = (CardView) findViewById(R.id.card_attendance_fallout);
        mLeaveSummaryCardView = (CardView) findViewById(R.id.card_leave_summary);
        mAttendanceCardviw = (CardView) findViewById(R.id.card_attendance_summary);


        mAttendanceCardviw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* progressDialog.setMessage("wait a  few seceonds Internet Slow");
                progressDialog.show();*/
               /* getFragmentManager().beginTransaction().replace(R.id.frame, new AttendanceSummaryFragment
                        (DashBoardActivity.this,progressDialog)).commit();*/
                Intent i= new Intent(DashBoardActivity.this,AtndSummaryActivity.class);
            }
        });

        mFalloutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DashboardFallout", "onClick: "+totalfalloutemp);

                if (isListenerActive){
                 //   getFragmentManager().beginTransaction().replace(R.id.frame,  FalloutFragment.newInstance(totalfalloutemp,totalemp)).commit();
                    getFragmentManager().beginTransaction().replace(R.id.frame,  new FalloutFragment(progressDialog, totalfalloutemp, totalemp)).commit();
                    /* progressDialog.setMessage("wait a  few seceonds");
                     progressDialog.show();*/

                }
                else {
                    Toast.makeText(DashBoardActivity.this, "wait few secs", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mLeaveSummaryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick: DATAT:::::"+leavecard);
                if (isListenerActive) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, LeaveFragment.newInstance(leavecard, totalemp)).commit();
                }else {
                    Toast.makeText(DashBoardActivity.this, "WAIT SOME TIME", Toast.LENGTH_SHORT).show();
                }
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.navg__attendence, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        return true;
    }

    //return super.onOptionsItemSelected(item);
    //   }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();


        if (id == R.id.nav_dashboard) {
            // Handle the camera action
            mToolbar.setTitle(item.getTitle().toString());
            Intent i = new Intent(DashBoardActivity.this, DashBoardActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_enginerers) {
            mToolbar.setTitle(item.getTitle().toString());
        } else if (id == R.id.nav_attendence) {
            mToolbar.setTitle(item.getTitle().toString());
           // getFragmentManager().beginTransaction().replace(R.id.frame, new AttendanceSummaryFragment
            // (this,progressDialog)).commit();
            Intent i= new Intent(DashBoardActivity.this,AtndSummaryActivity.class);
        } else if (id == R.id.nav_reports) {
            mToolbar.setTitle(item.getTitle().toString());
        } else if (id == R.id.nav_clients) {

            mToolbar.setTitle(item.getTitle().toString());

        } else if (id == R.id.logout) {

            mToolbar.setTitle(item.getTitle().toString());
            Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
            startActivity(intent);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void content(DashBoardModel dashBoardModel) {

        dashBoardModel.getmTimeStamp();
        mark = dashBoardModel.getmMarked();
        mUnmark = dashBoardModel.getmUnmarked();
        totalfalloutemp = dashBoardModel.getmFalloutEmployee();
        totalemp = dashBoardModel.getmTotalEmployee();
        leavecard = dashBoardModel.getmLeave();
        Log.i("DashView", "content leave: " + dashBoardModel.getmLeave());
        Log.i("DashView", "content: " + dashBoardModel.getmTotalEmployee());
        Log.i("DashView", "content: " + dashBoardModel.getmUnmarked());
        Log.i("DashView", "content: " + dashBoardModel.getmMarked());
        unmarkCardButton.setText(mUnmark);
        markCardButton.setText("" + mark);
        faloutempTv.setText("" + totalfalloutemp);
        totalEmpTv.setText("out of " + totalemp);
        leaveEmpTv.setText(leavecard);
        isListenerActive = true;


        Toast.makeText(DashBoardActivity.this, "data is available", Toast.LENGTH_SHORT).show();
    }

}
