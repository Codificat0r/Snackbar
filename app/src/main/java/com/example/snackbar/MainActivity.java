package com.example.snackbar;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnShow;
    private Button btnChange;
    private Button btnParent;
    private LinearLayout linearLayout;
    private CoordinatorLayout coordinatorLayout;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);

        btnShow = (Button) findViewById(R.id.btnShow);
        btnChange = (Button) findViewById(R.id.btnChange);
        btnParent = (Button) findViewById(R.id.btnParent);

        linearLayout = (LinearLayout) findViewById(R.id.parentLinearLayout);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        btnShow.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnParent.setOnClickListener(this);
    }

    View.OnClickListener snackbarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            snackbar.dismiss();
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnShow:
                Snackbar.make(v, "Mensaje en coordinator", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.btnChange:
                snackbar = Snackbar.make(coordinatorLayout, "Mensaje con otro color", Snackbar.LENGTH_INDEFINITE).setAction("Listo", snackbarClickListener);
                snackbar.setActionTextColor(Color.GREEN);
                View view1 = snackbar.getView();
                view1.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                snackbar.show();
                break;
            case R.id.btnParent:
                //Le decimos que se cree en linearLayot (una vista), su mensaje, su duracion. Y le ponemos como texto
                //de accion "Listo" y le decimos que usará el OnClickListener que hemos hecho justo arriba de este OnClick.
                snackbar = Snackbar.make(linearLayout, "Mensaje en LinearLayout", Snackbar.LENGTH_INDEFINITE).setAction("Listo", snackbarClickListener);
                //Ponemos el color del texto de Action (el Listo para quitar el mensaje)
                snackbar.setActionTextColor(Color.GREEN);
                //Conseguimos la referencia a la direccion de memoria que contiene la vista del elemento gráfico del Snackbar
                View view2 = snackbar.getView();
                //Le cambiamos el color de fondo a la vista del Snackbar.
                view2.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                //Lo mostramos
                snackbar.show();
                break;
        }
    }
}
