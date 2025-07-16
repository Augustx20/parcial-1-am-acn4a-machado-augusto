package augusto.machado;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TareasActivity extends AppCompatActivity {
    private LinearLayout layoutTareas;
    private FirebaseFirestore db;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas); // El layout corregido

        layoutTareas = findViewById(R.id.layoutTareas);
        db = FirebaseFirestore.getInstance();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        cargarTareas();
    }

    private void cargarTareas() {
        db.collection("users").document(uid).collection("tareas")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {

                        String idTarea = doc.getId();
                        String titulo = doc.getString("titulo");
                        String fechaInicio = doc.getString("fechaInicio");
                        String fechaFin = doc.getString("fechaFin");
                        String raza = doc.getString("raza");
                        String anio = doc.getString("año");
                        String peso = doc.getString("peso");
                        String altura = doc.getString("altura");

                        // Crear el contenedor de la tarea
                        LinearLayout card = new LinearLayout(this);
                        card.setOrientation(LinearLayout.VERTICAL);
                        card.setPadding(16, 16, 16, 16);
                        card.setBackground(ContextCompat.getDrawable(this, R.drawable.rgb_shape));



                        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        cardParams.setMargins(0, 0, 0, 24);
                        card.setLayoutParams(cardParams);

                        TextView texto = new TextView(this);
                        texto.setText(
                                "Título: " + titulo + "\n" +
                                        "Inicio: " + fechaInicio + "\n" +
                                        "Fin: " + fechaFin + "\n" +
                                        "Raza: " + raza + "\n" +
                                        "Años: " + anio + "\n" +
                                        "Peso: " + peso + "\n" +
                                        "Altura: " + altura
                        );
                        texto.setTextSize(16);

                        card.addView(texto);
                        layoutTareas.addView(card);

                        // Datos actuales de la tarea en un Map para pasarlos al diálogo
                        Map<String, Object> tareaActual = new HashMap<>();
                        tareaActual.put("titulo", titulo);
                        tareaActual.put("fechaInicio", fechaInicio);
                        tareaActual.put("fechaFin", fechaFin);
                        tareaActual.put("raza", raza);
                        tareaActual.put("año", anio);
                        tareaActual.put("peso", peso);
                        tareaActual.put("altura", altura);

                        // Al hacer click, se muestra el diálogo de opciones
                        card.setOnClickListener(v -> {
                            new AlertDialog.Builder(this)
                                    .setTitle("Opciones")
                                    .setItems(new String[]{"Editar tarea", "Eliminar tarea", "Cancelar"}, (dialog, which) -> {
                                        switch (which) {
                                            case 0:
                                                mostrarDialogoEditarTarea(this, uid, idTarea, tareaActual, this::cargarTareas);
                                                break;
                                            case 1:
                                                new AlertDialog.Builder(this)
                                                        .setTitle("Confirmar eliminación")
                                                        .setMessage("¿Seguro que querés eliminar esta tarea?")
                                                        .setPositiveButton("Eliminar", (d, w) -> {
                                                            db.collection("users").document(uid).collection("tareas").document(idTarea)
                                                                    .delete()
                                                                    .addOnSuccessListener(aVoid -> {
                                                                        Toast.makeText(this, "Tarea eliminada", Toast.LENGTH_SHORT).show();
                                                                        layoutTareas.removeView(card);
                                                                    })
                                                                    .addOnFailureListener(e -> {
                                                                        Toast.makeText(this, "Error al eliminar tarea", Toast.LENGTH_SHORT).show();
                                                                    });
                                                        })
                                                        .setNegativeButton("Cancelar", null)
                                                        .show();
                                                break;
                                        }
                                    })
                                    .show();
                        });
                    }

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al cargar tareas", Toast.LENGTH_SHORT).show();
                });
    }
    private void mostrarDialogoEditarTarea(Context context, String uid, String idTarea, Map<String, Object> tareaActual, Runnable onActualizado) {

        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(16, 16, 16, 16);

        EditText inputTitulo = new EditText(context);
        inputTitulo.setHint("Título");
        inputTitulo.setText((String) tareaActual.get("titulo"));
        layout.addView(inputTitulo);

        EditText inputFechaInicio = new EditText(context);
        inputFechaInicio.setHint("Fecha de inicio");
        inputFechaInicio.setText((String) tareaActual.get("fechaInicio"));
        layout.addView(inputFechaInicio);

        EditText inputFechaFin = new EditText(context);
        inputFechaFin.setHint("Fecha de fin");
        inputFechaFin.setText((String) tareaActual.get("fechaFin"));
        layout.addView(inputFechaFin);

        EditText inputRaza = new EditText(context);
        inputRaza.setHint("Raza");
        inputRaza.setText((String) tareaActual.get("raza"));
        layout.addView(inputRaza);

        EditText inputAnio = new EditText(context);
        inputAnio.setHint("Años");
        inputAnio.setText((String) tareaActual.get("año"));
        layout.addView(inputAnio);

        EditText inputPeso = new EditText(context);
        inputPeso.setHint("Peso");
        inputPeso.setText((String) tareaActual.get("peso"));
        layout.addView(inputPeso);

        EditText inputAltura = new EditText(context);
        inputAltura.setHint("Altura");
        inputAltura.setText((String) tareaActual.get("altura"));
        layout.addView(inputAltura);

        new AlertDialog.Builder(context)
                .setTitle("Editar tarea")
                .setView(layout)
                .setPositiveButton("Guardar", (dialog, which) -> {

                    Map<String, Object> tareaActualizada = new HashMap<>();
                    tareaActualizada.put("titulo", inputTitulo.getText().toString().trim());
                    tareaActualizada.put("fechaInicio", inputFechaInicio.getText().toString().trim());
                    tareaActualizada.put("fechaFin", inputFechaFin.getText().toString().trim());
                    tareaActualizada.put("raza", inputRaza.getText().toString().trim());
                    tareaActualizada.put("año", inputAnio.getText().toString().trim());
                    tareaActualizada.put("peso", inputPeso.getText().toString().trim());
                    tareaActualizada.put("altura", inputAltura.getText().toString().trim());

                    FirebaseFirestore.getInstance()
                            .collection("users").document(uid).collection("tareas").document(idTarea)
                            .update(tareaActualizada)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(context, "Tarea actualizada", Toast.LENGTH_SHORT).show();
                                if (onActualizado != null) onActualizado.run();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(context, "Error al actualizar tarea", Toast.LENGTH_SHORT).show();
                            });
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    public void cerrarSesion(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(TareasActivity.this, MainActivity.class); // O tu pantalla de login
        startActivity(intent);
        finish();
    }

    public void volverAlHome(View view) {
        Intent intent = new Intent(TareasActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

}