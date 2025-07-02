package augusto.machado;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void updateMessage(View view){
        Toast.makeText(this, "Nueva Tarea", Toast.LENGTH_LONG).show();
        LinearLayout title = findViewById(R.id.linearLayout);

        // Llama correctamente al método pasando el contexto y el layout padre
        crearCard(this, title);
    }

    public void crearCard(Context context, ViewGroup parentLayout) {
        LinearLayout card = new LinearLayout(context);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setPadding(16, 16, 16, 16);
        card.setBackground(ContextCompat.getDrawable(context, R.drawable.rg_shape));

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(16, 16, 16, 16);
        card.setLayoutParams(cardParams);

        // Columna izquierda (un poco más ancha)
        LinearLayout columnaIzquierda = new LinearLayout(context);
        columnaIzquierda.setOrientation(LinearLayout.VERTICAL);
        columnaIzquierda.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.5f));

        EditText tituloEdit = new EditText(new ContextThemeWrapper(context,R.style.EditTextSinLinea));
        tituloEdit.setHint("Título");
        tituloEdit.setTextSize(14);
        tituloEdit.setInputType(InputType.TYPE_CLASS_TEXT);
        tituloEdit.setBackground(null);
        ajustarEditText(tituloEdit);

        EditText fechaInicio = new EditText(new ContextThemeWrapper(context,R.style.EditTextSinLinea));
        fechaInicio.setHint("Fecha de inicio");
        configurarSelectorFecha(fechaInicio);
        fechaInicio.setBackground(null);
        ajustarEditText(fechaInicio);

        EditText fechaFin = new EditText(new ContextThemeWrapper(context,R.style.EditTextSinLinea));
        fechaFin.setHint("Fecha de fin");
        configurarSelectorFecha(fechaFin);
        fechaFin.setBackground(null);
        ajustarEditText(fechaFin);

        columnaIzquierda.addView(tituloEdit);
        columnaIzquierda.addView(fechaInicio);
        columnaIzquierda.addView(fechaFin);

        // Separador vertical
        View separador1 = new View(context);
        LinearLayout.LayoutParams sepParams1 = new LinearLayout.LayoutParams(2, ViewGroup.LayoutParams.MATCH_PARENT);
        sepParams1.setMargins(12, 0, 12, 0);
        separador1.setLayoutParams(sepParams1);
        separador1.setBackgroundColor(Color.DKGRAY);

        // Columna central
        LinearLayout columnaCentro = new LinearLayout(context);
        columnaCentro.setOrientation(LinearLayout.VERTICAL);
        columnaCentro.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        EditText razaEdit = new EditText(new ContextThemeWrapper(context,R.style.EditTextSinLinea));
        razaEdit.setHint("Raza");
        razaEdit.setTextSize(10);
        razaEdit.setInputType(InputType.TYPE_CLASS_TEXT);
        razaEdit.setBackground(null);
        ajustarEditText(razaEdit);

        columnaCentro.addView(razaEdit);

        EditText AnioEdit = new EditText(new ContextThemeWrapper(context,R.style.EditTextSinLinea));
        AnioEdit.setHint("Año");
        AnioEdit.setTextSize(10);
        AnioEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        AnioEdit.setBackground(null);
        ajustarEditText(AnioEdit);

        columnaCentro.addView(AnioEdit);

        EditText PesoEdit = new EditText(new ContextThemeWrapper(context,R.style.EditTextSinLinea));
        PesoEdit.setHint("kg");
        PesoEdit.setTextSize(10);
        PesoEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        PesoEdit.setBackground(null);
        ajustarEditText(PesoEdit);

        columnaCentro.addView(PesoEdit);

        EditText AlturaEdit = new EditText(new ContextThemeWrapper(context,R.style.EditTextSinLinea));
        AlturaEdit.setHint("cm");
        AlturaEdit.setTextSize(10);
        AlturaEdit.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlturaEdit.setBackground(null);
        ajustarEditText(AlturaEdit);

        columnaCentro.addView(AlturaEdit);

        // Separador vertical
        View separador2 = new View(context);
        LinearLayout.LayoutParams sepParams2 = new LinearLayout.LayoutParams(2, ViewGroup.LayoutParams.MATCH_PARENT);
        sepParams2.setMargins(12, 0, 12, 0);
        separador2.setLayoutParams(sepParams2);
        separador2.setBackgroundColor(Color.DKGRAY);

        // Columna derecha
        LinearLayout columnaDerecha = new LinearLayout(context);
        columnaDerecha.setOrientation(LinearLayout.VERTICAL);
        columnaDerecha.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));


        EditText notasEdit = new EditText(new ContextThemeWrapper(context,R.style.EditTextSinLinea));
        notasEdit.setHint("Notas adicionales");
        notasEdit.setTextSize(12);
        notasEdit.setPadding(4, 8, 4, 8);
        notasEdit.setBackground(null);
        columnaDerecha.addView(notasEdit);


        // Agregar todo al card
        card.addView(columnaIzquierda);
        card.addView(separador1);
        card.addView(columnaCentro);
        card.addView(separador2);
        card.addView(columnaDerecha);

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

    private void configurarSelectorFecha(EditText campoFecha) {
        campoFecha.setFocusable(false);
        campoFecha.setClickable(true);

        campoFecha.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int anio = calendar.get(Calendar.YEAR);
            int mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    campoFecha.getContext(),
                    (view, year, month, dayOfMonth) -> {
                        String fechaSeleccionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                        campoFecha.setText(fechaSeleccionada);
                    },
                    anio, mes, dia
            );
            datePickerDialog.show();
        });
    }
    private View separadorHorizontal(Context context) {
        View separador = new View(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // Ancho completo
                3 // Grosor del separador, lo podés ajustar
        );
        params.setMargins(0, 12, 0, 12); // Margen arriba y abajo
        separador.setLayoutParams(params);
        separador.setBackgroundColor(Color.GRAY); // Color del separador
        return separador;
    }
    private void ajustarEditText(EditText editText) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 8, 0, 8);
        editText.setLayoutParams(params);
        editText.setTextSize(14);
    }

}
