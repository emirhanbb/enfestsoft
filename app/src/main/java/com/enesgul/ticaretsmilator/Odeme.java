package com.enesgul.ticaretsmilator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Odeme extends AppCompatActivity  implements BillingProcessor.IBillingHandler{
    BottomNavigationView bottomNavigationView;
    BillingProcessor bp;
    CardView oyunParasi, bir, iki, uc, dort, bes, alti, yedi,sekiz,dokuz;
    TextView txtDisplay;
    private TransactionDetails purchaseTransactionDetails = null;
    private static final String TAG = "Odeme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odeme);




        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgIpkGywH5XXA4ftJtPj4NrhJ5Gn3HqpQkoVWQi6z6CRpmDRyaIfrvQxaAo5obK8StKbaxir27wtZJS4P64MWD2Eb2LhkrdrQAe25cOtYv04ZbwSIXz4145BUKIsc6gPUmDRqy/0aVKRdsI2jkn3KlgmAYmLm3Hxd67y83iUguQzwS9qKsgq8qbFxvc/PL7/KDg++UwGtKu8AcSMbPUwHOGaFtggydinKf85GEYCNrBCY0PgNiqCnxzAbWjAniTrbQw88ZOp11UueqDdQo+a1/RYiirw4Jn3BozVuT2CgHPWnGQbWAkK6aJuSwyzPDep8HGejIqOIIGIMRes0+WJSbQIDAQAB", this);
        bp.initialize();
        bottomNavigationView = findViewById(R.id.bottommenu);
        bottomNavigationView.setOnNavigationItemSelectedListener(menulistener);
        oyunParasi = findViewById(R.id.oyunParasi);
        bir = findViewById(R.id.bir);
        iki = findViewById(R.id.iki);
        uc = findViewById(R.id.uc);
        dort = findViewById(R.id.dort);
        bes = findViewById(R.id.bes);
        alti = findViewById(R.id.alti);
        yedi = findViewById(R.id.yedi);
        sekiz = findViewById(R.id.sekiz);
        dokuz = findViewById(R.id.dokuz);


    }


    private BottomNavigationView.OnNavigationItemSelectedListener menulistener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){

                case R.id.mesajlar:
                    Intent intent = new Intent(Odeme.this,Main2Activity.class);
                    intent.putExtra("link","https://enfessoft.com/ticaretsimulator/gelenkutusu.php");
                    startActivity(intent);
                    break;
                case R.id.siralama:
                    Intent intent2 = new Intent(Odeme.this,Main2Activity.class);
                    intent2.putExtra("link","https://enfessoft.com/ticaretsimulator/siralama.php");
                    startActivity(intent2);
                    break;

                case R.id.sohbet:
                    Intent intent3 = new Intent(Odeme.this,Main2Activity.class);
                    intent3.putExtra("link","https://enfessoft.com/ticaretsimulator/sohbet.php");
                    startActivity(intent3);
                    break;
            }
            return true;
        }
    };


    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {
        details.purchaseInfo.signature.toString();

        Intent intent = new Intent(Odeme.this,Main2Activity.class);
        intent.putExtra("link","https://enfessoft.com/ticaretsimulator/market.php?ürünadi=" +productId + "-" + details.purchaseInfo.purchaseData.orderId);
        startActivity(intent);
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {

    }
    private boolean hasSubscription() {

        if (purchaseTransactionDetails != null) {
            return purchaseTransactionDetails.purchaseInfo != null;
        }
        return false;
    }
    @Override
    public void onBillingInitialized() {
        Log.d("Odeme", "onBillingInitialized: ");

        final String milyaroyunparasi1 = "milyaroyunparasi1";
        final String elmas15 = "elmas15";
        final String elmas25 = "elmas25";
        final String elmas50 = "elmas50";
        final String elmas100 = "elmas100";
        final String elmas220 = "elmas220";
        final String elmas330 = "elmas330";
        final String booster3gun = "booster3gun";
        final String booster1hafta = "booster1hafta";
        final String booster1ay = "booster1ay";



       // purchaseTransactionDetails = bp.getSubscriptionTransactionDetails(milyaroyunparasi1);


        bp.loadOwnedPurchasesFromGoogle();

        oyunParasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bp.isOneTimePurchaseSupported()) {
                    bp.purchase(Odeme.this, milyaroyunparasi1);
                } else {
                    Log.d("Odeme", "onBillingInitialized: Subscription updated is not supported");
                }
            }
        });



        bir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bp.isOneTimePurchaseSupported()) {
                    bp.purchase(Odeme.this, elmas15);
                } else {
                    Log.d("Odeme", "onBillingInitialized: Subscription updated is not supported");
                }
            }
        });

        iki.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (bp.isOneTimePurchaseSupported()) {
                    bp.purchase(Odeme.this, elmas25);
                } else {
                    Log.d("Odeme", "onBillingInitialized: Subscription updated is not supported");
                }
            }
        });

        uc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (bp.isOneTimePurchaseSupported()) {
                    bp.purchase(Odeme.this, elmas50);
                } else {
                    Log.d("Odeme", "onBillingInitialized: Subscription updated is not supported");
                }
            }
        });


        dort.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (bp.isOneTimePurchaseSupported()) {
                    bp.purchase(Odeme.this, elmas100);
                } else {
                    Log.d("Odeme", "onBillingInitialized: Subscription updated is not supported");
                }
            }
        });

        bes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (bp.isOneTimePurchaseSupported()) {
                    bp.purchase(Odeme.this, elmas220);
                } else {
                    Log.d("Odeme", "onBillingInitialized: Subscription updated is not supported");
                }
            }
        });

        alti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (bp.isOneTimePurchaseSupported()) {
                    bp.purchase(Odeme.this, elmas330);
                } else {
                    Log.d("Odeme", "onBillingInitialized: Subscription updated is not supported");
                }
            }
        });

        yedi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (bp.isOneTimePurchaseSupported()) {
                    bp.purchase(Odeme.this, booster3gun);
                } else {
                    Log.d("Odeme", "onBillingInitialized: Subscription updated is not supported");
                }
            }
        });

        sekiz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (bp.isOneTimePurchaseSupported()) {
                    bp.purchase(Odeme.this, booster1hafta);
                } else {
                    Log.d("Odeme", "onBillingInitialized: Subscription updated is not supported");
                }
            }
        });

        dokuz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (bp.isOneTimePurchaseSupported()) {
                    bp.purchase(Odeme.this, booster1ay);
                } else {
                    Log.d("Odeme", "onBillingInitialized: Subscription updated is not supported");
                }
            }
        });


    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Odeme.this,OdemeTuru.class);
        startActivity(intent);
        finish();
        }



}
