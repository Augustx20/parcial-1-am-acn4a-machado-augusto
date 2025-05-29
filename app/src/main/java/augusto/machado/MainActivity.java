package augusto.machado;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }
    public void updateMessage(View view){
        Toast.makeText(this, "Nueva Tarea", Toast.LENGTH_LONG).show();
        LinearLayout title = findViewById(R.id.linearLayout);

        // Llama correctamente al método pasando el contexto y el layout padre
        crearCard(this, title);
    }

    public void crearCard(Context context, ViewGroup parentLayout) {
        // Card contenedor
        LinearLayout card = new LinearLayout(context);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setPadding(24, 24, 24, 24);
        card.setBackground(ContextCompat.getDrawable(context, R.drawable.rg_shape)); // Fondo con esquinas redondeadas

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(16, 16, 16, 16);
        card.setLayoutParams(cardParams);

        // Columna izquierda
        LinearLayout columnaIzquierda = new LinearLayout(context);
        columnaIzquierda.setOrientation(LinearLayout.VERTICAL);
        columnaIzquierda.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2));

        TextView titulo = new TextView(context);
        titulo.setText("Título");
        titulo.setTextSize(18);
        titulo.setTypeface(null, Typeface.BOLD);

        TextView fecha1 = new TextView(context);
        fecha1.setText("5 may 2025   00:00");

        TextView fecha2 = new TextView(context);
        fecha2.setText("10 may 2025  10:00");

        columnaIzquierda.addView(titulo);
        columnaIzquierda.addView(fecha1);
        columnaIzquierda.addView(fecha2);

        // Separador vertical
        View separador1 = new View(context);
        separador1.setLayoutParams(new LinearLayout.LayoutParams(2, ViewGroup.LayoutParams.MATCH_PARENT));
        separador1.setBackgroundColor(Color.BLACK);

        // Columna central
        LinearLayout columnaCentro = new LinearLayout(context);
        columnaCentro.setOrientation(LinearLayout.VERTICAL);
        columnaCentro.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2));

        columnaCentro.addView(textoLabel(context, "Raza"));
        columnaCentro.addView(textoLabel(context, "Años"));
        columnaCentro.addView(textoLabel(context, "Peso"));
        columnaCentro.addView(textoLabel(context, "Altura"));

        // Separador vertical
        View separador2 = new View(context);
        separador2.setLayoutParams(new LinearLayout.LayoutParams(2, ViewGroup.LayoutParams.MATCH_PARENT));
        separador2.setBackgroundColor(Color.BLACK);

        // Columna derecha
        LinearLayout columnaDerecha = new LinearLayout(context);
        columnaDerecha.setOrientation(LinearLayout.VERTICAL);
        columnaDerecha.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 2));

        TextView notas = new TextView(context);
        notas.setText("notes");
        columnaDerecha.addView(notas);

        // Agregar columnas a la card
        card.addView(columnaIzquierda);
        card.addView(separador1);
        card.addView(columnaCentro);
        card.addView(separador2);
        card.addView(columnaDerecha);

        // Agregar la card al layout padre
        parentLayout.addView(card);
    }

    // Método auxiliar para crear labels simples
    private TextView textoLabel(Context context, String texto) {
        TextView label = new TextView(context);
        label.setText(texto);
        label.setTextSize(14);
        label.setPadding(0, 8, 0, 8);
        return label;
    }


}