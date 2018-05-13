package com.chiapperoivan.aplicacioncongreso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Addition6: Plugin Crashlytics Fabric
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
//Addition6 Fin

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Addition7: Plugin Crashlytics Fabric
        Fabric.with(this, new Crashlytics());
        //Addition7 Fin

        setContentView(R.layout.activity_menu_principal);
    }
}
