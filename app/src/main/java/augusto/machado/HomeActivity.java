package augusto.machado;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = findViewById(R.id.calendarView_test);
        calendarView.setDate(System.currentTimeMillis(), false, true);


    }

    public void updateMessage(View view){
        Toast.makeText(this, "Nueva Tarea", Toast.LENGTH_LONG).show();
        LinearLayout title = findViewById(R.id.linearLayout);

        // Llama correctamente al método pasando el contexto y el layout padre
        crearCard(this, title);
    }
    public void OpenActivityTareas(View view) {
        Intent intent = new Intent(HomeActivity.this, TareasActivity.class);
        startActivity(intent);
        finish();
    }
    public void cerrarSesion(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class); // O tu pantalla de login
        startActivity(intent);
        finish();
    }
    public void crearCard(Context context, ViewGroup parentLayout) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Card contenedor
        LinearLayout card = new LinearLayout(context);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setPadding(16, 16, 16, 16);
        card.setBackground(ContextCompat.getDrawable(context, R.drawable.rgb_shape));

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(16, 16, 16, 16);
        card.setLayoutParams(cardParams);

        // Columna izquierda
        LinearLayout columnaIzquierda = new LinearLayout(context);
        columnaIzquierda.setOrientation(LinearLayout.VERTICAL);
        columnaIzquierda.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.5f));

        EditText tituloEdit = new EditText(context);
        tituloEdit.setHint("Título");
        tituloEdit.setTextSize(14);
        tituloEdit.setInputType(InputType.TYPE_CLASS_TEXT);
        tituloEdit.setBackground(null);
        ajustarEditText(tituloEdit);

        EditText fechaInicio = new EditText(context);
        fechaInicio.setHint("Fecha de inicio");
        configurarSelectorFecha(fechaInicio);
        fechaInicio.setBackground(null);
        ajustarEditText(fechaInicio);

        EditText fechaFin = new EditText(context);
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

        EditText razaEdit = new EditText(context);
        razaEdit.setHint("Raza");
        razaEdit.setTextSize(12);
        razaEdit.setInputType(InputType.TYPE_CLASS_TEXT);
        ajustarEditText(razaEdit);
        columnaCentro.addView(razaEdit);

        EditText AniosEdit = new EditText(context);
        AniosEdit.setHint("Años");
        AniosEdit.setTextSize(12);
        AniosEdit.setInputType(InputType.TYPE_CLASS_TEXT);
        ajustarEditText(AniosEdit);
        columnaCentro.addView(AniosEdit);

        EditText PesoEdit = new EditText(context);
        PesoEdit.setHint("Peso");
        PesoEdit.setTextSize(12);
        PesoEdit.setInputType(InputType.TYPE_CLASS_TEXT);
        ajustarEditText(PesoEdit);
        columnaCentro.addView(PesoEdit);

        EditText AlturaEdit = new EditText(context);
        AlturaEdit.setHint("Altura");
        AlturaEdit.setTextSize(12);
        AlturaEdit.setInputType(InputType.TYPE_CLASS_TEXT);
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

        TextView notas = new TextView(context);
        notas.setText("Notas adicionales...");
        notas.setTextSize(14);
        notas.setPadding(4, 8, 4, 8);
        columnaDerecha.addView(notas);

        // Botón Enviar dentro de la columna derecha
        Button botonEnviar = new Button(context);
        botonEnviar.setText("Enviar Tarea");
        columnaDerecha.addView(botonEnviar);

        // Agregar columnas al card
        card.addView(columnaIzquierda);
        card.addView(separador1);
        card.addView(columnaCentro);
        card.addView(separador2);
        card.addView(columnaDerecha);

        parentLayout.addView(card);

        // Lógica del botón
        botonEnviar.setOnClickListener(v -> {

            String titulo = tituloEdit.getText().toString().trim();
            String fechaInicioStr = fechaInicio.getText().toString().trim();
            String fechaFinStr = fechaFin.getText().toString().trim();
            //Animal
            String raza = razaEdit.getText().toString().trim();
            String anio = AniosEdit.getText().toString().trim();
            String peso = PesoEdit.getText().toString().trim();
            String altura = AlturaEdit.getText().toString().trim();

            if (titulo.isEmpty() || fechaInicioStr.isEmpty() || fechaFinStr.isEmpty()
                ||raza.isEmpty() || anio.isEmpty() || peso.isEmpty() || altura.isEmpty()) {
                Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }


            Map<String, Object> tarea = new HashMap<>();
            tarea.put("titulo", titulo);
            tarea.put("fechaInicio", fechaInicioStr);
            tarea.put("fechaFin", fechaFinStr);
            tarea.put("raza",raza);
            tarea.put("año",anio);
            tarea.put("peso",peso);
            tarea.put("altura",altura);


            agregarTarea(context,db,uid,tarea,card,parentLayout);

        });
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
    private void agregarTarea(Context context,FirebaseFirestore db,String uid, Map<String,Object> tarea,LinearLayout card,ViewGroup parentLayout) {
        db.collection("users").document(uid)
                .collection("tareas")
                .add(tarea)
                .addOnSuccessListener(documentReference -> {

                    String idTarea = documentReference.getId();
                    Toast.makeText(context, "Tarea enviada", Toast.LENGTH_SHORT).show();
                    parentLayout.removeView(card);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Error al enviar tarea", Toast.LENGTH_SHORT).show();
                });
    }





}
