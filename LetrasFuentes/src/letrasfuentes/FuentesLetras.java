package letrasfuentes;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class FuentesLetras {

    private JFrame ventana;

    private JPanel panelPruebas;

    private JLabel labelTitulo;

    private JLabel labelTiposLetra;
    private JLabel labelDimensionesLetra;
    private JLabel labelEstilosLetras;

    private JComboBox boxTiposLetras;
    private JSpinner spinnerDimensionesLetras;
    private SpinnerNumberModel modeloSpinnerDimenisones;

    private JRadioButton buttonBold;
    private JRadioButton buttonNormal;
    private JRadioButton buttonItalic;

    private ButtonGroup estilosLetra;

    private JLabel labelColoresLetras;
    private JLabel labelColoresFondo;
    private JLabel labelRTexto;
    private JLabel labelGTexto;
    private JLabel labelBTexto;
    private JLabel labelRFondo;
    private JLabel labelGFondo;
    private JLabel labelBFondo;

    private JSlider sliderRTexto;
    private JSlider sliderGTexto;
    private JSlider sliderBTexto;
    private JSlider sliderRFondo;
    private JSlider sliderGFondo;
    private JSlider sliderBFondo;

    private JScrollPane scrollColoresLetras;
    private JScrollPane scrollColoresFondo;

    private JList<String> listColoresLetras;
    private JList<String> listaColoresFondo;

    private JLabel labelTextoPrueba;

    // Letras
    String[] letras;
    String[] nombresColores;
    Colores[] colores;

    public FuentesLetras() {
        this.ventana = new JFrame("Letras Fuentes");
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        
        this.letras = ge.getAvailableFontFamilyNames();

        // Encabezado
        this.labelTitulo = new JLabel("Tipos de letras y colores");

        // Propiedades de las letras
        this.labelTiposLetra = new JLabel("Tipos de letras");
        this.labelDimensionesLetra = new JLabel("Tamaño");
        this.labelEstilosLetras = new JLabel("Estilo de letras");

        this.boxTiposLetras = new JComboBox(this.letras);

        this.modeloSpinnerDimenisones = new SpinnerNumberModel(16, -1, 301, 1);
        this.spinnerDimensionesLetras = new JSpinner(this.modeloSpinnerDimenisones);

        this.buttonBold = new JRadioButton("Bold");
        this.buttonNormal = new JRadioButton("Normal");
        this.buttonItalic = new JRadioButton("Italic");

        this.estilosLetra = new ButtonGroup();
        this.estilosLetra.add(this.buttonBold);
        this.estilosLetra.add(this.buttonNormal);
        this.estilosLetra.add(this.buttonItalic);

        // Propiedades de colores
        this.labelColoresLetras = new JLabel("Color texto");

        colores = Colores.values();

        nombresColores = new String[colores.length];

        for (int i = 0; i < colores.length; i++) {
            nombresColores[i] = colores[i].toString();
        }

        this.sliderRTexto = new JSlider(JSlider.VERTICAL, 0, 255, 127);
        this.sliderGTexto = new JSlider(JSlider.VERTICAL, 0, 255, 127);
        this.sliderBTexto = new JSlider(JSlider.VERTICAL, 0, 255, 127);

        this.labelRTexto = new JLabel("R");
        this.labelGTexto = new JLabel("G");
        this.labelBTexto = new JLabel("B");

        this.listColoresLetras = new JList<>(this.nombresColores);

        this.scrollColoresLetras = new JScrollPane(this.listColoresLetras);

        this.labelColoresFondo = new JLabel("Color Fondo");

        this.sliderRFondo = new JSlider(JSlider.VERTICAL, 0, 255, 127);
        this.sliderGFondo = new JSlider(JSlider.VERTICAL, 0, 255, 127);
        this.sliderBFondo = new JSlider(JSlider.VERTICAL, 0, 255, 127);

        this.labelRFondo = new JLabel("R");
        this.labelGFondo = new JLabel("G");
        this.labelBFondo = new JLabel("B");

        this.listaColoresFondo = new JList<>(this.nombresColores);

        this.scrollColoresFondo = new JScrollPane(this.listaColoresFondo);

        // Panel de prueba
        this.panelPruebas = new JPanel();

        this.labelTextoPrueba = new JLabel("Texto de prueba");

        atributos();
        armado();
        escuchas();
        mostrar();
    }

    public void atributos() {
        this.ventana.setSize(450, 510);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setLayout(null);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.listColoresLetras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.labelTitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        this.labelTitulo.setForeground(Color.MAGENTA);

        this.buttonNormal.setSelected(true);

        this.labelTitulo.setBounds(120, 10, 200, 20);

        this.labelTiposLetra.setBounds(40, 70, 122, 20);
        this.boxTiposLetras.setBounds(142, 70, 50, 20);

        this.labelDimensionesLetra.setBounds(40, 95, 122, 20);
        this.spinnerDimensionesLetras.setBounds(142, 95, 50, 20);

        this.labelEstilosLetras.setBounds(270, 70, 122, 20);
        this.buttonBold.setBounds(210, 95, 70, 20);
        this.buttonNormal.setBounds(280, 95, 70, 20);
        this.buttonItalic.setBounds(350, 95, 90, 20);

        this.labelColoresLetras.setBounds(100, 140, 122, 20);

        this.labelRTexto.setBounds(45, 170, 20, 20);
        this.labelGTexto.setBounds(65, 170, 20, 20);
        this.labelBTexto.setBounds(85, 170, 20, 20);

        this.sliderRTexto.setBounds(40, 190, 20, 120);
        this.sliderGTexto.setBounds(60, 190, 20, 120);
        this.sliderBTexto.setBounds(80, 190, 20, 120);

        this.scrollColoresLetras.setBounds(100, 190, 100, 110);

        this.labelColoresFondo.setBounds(272, 140, 122, 20);

        this.labelRFondo.setBounds(235, 170, 20, 20);
        this.labelGFondo.setBounds(255, 170, 20, 20);
        this.labelBFondo.setBounds(275, 170, 20, 20);
        this.sliderRFondo.setBounds(230, 190, 20, 120);
        this.sliderGFondo.setBounds(250, 190, 20, 120);
        this.sliderBFondo.setBounds(270, 190, 20, 120);

        this.scrollColoresFondo.setBounds(290, 190, 100, 110);

        this.panelPruebas.setSize(430, 160);
        this.panelPruebas.setBounds(10, 340, 415, 120);
        this.panelPruebas.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.panelPruebas.setBackground(Color.red);

    }

    public void armado() {

        this.ventana.add(this.labelTitulo);

        this.ventana.add(this.labelTiposLetra);
        this.ventana.add(this.boxTiposLetras);
        this.ventana.add(this.labelDimensionesLetra);
        this.ventana.add(this.spinnerDimensionesLetras);

        this.ventana.add(this.labelEstilosLetras);
        this.ventana.add(this.buttonBold);
        this.ventana.add(this.buttonNormal);
        this.ventana.add(this.buttonItalic);

        this.ventana.add(this.labelColoresLetras);
        this.ventana.add(this.sliderRTexto);
        this.ventana.add(this.sliderGTexto);
        this.ventana.add(this.sliderBTexto);
        this.ventana.add(this.labelRTexto);
        this.ventana.add(this.labelGTexto);
        this.ventana.add(this.labelBTexto);

        this.ventana.add(this.scrollColoresLetras);

        this.ventana.add(this.labelColoresFondo);
        this.ventana.add(this.sliderRFondo);
        this.ventana.add(this.sliderGFondo);
        this.ventana.add(this.sliderBFondo);
        this.ventana.add(this.labelRFondo);
        this.ventana.add(this.labelGFondo);
        this.ventana.add(this.labelBFondo);

        this.ventana.add(this.scrollColoresFondo);

        this.panelPruebas.add(this.labelTextoPrueba);
        this.ventana.add(this.panelPruebas);

    }

    public void escuchas() {
        this.boxTiposLetras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int estiloLetra = 0;
                String tipoLetra = (String) boxTiposLetras.getSelectedItem();
                int dimensionesLetra = (int) spinnerDimensionesLetras.getValue();

                if (buttonBold.isSelected()) {
                    estiloLetra = 1;
                }
                if (buttonNormal.isSelected()) {
                    estiloLetra = 0;
                }
                if (buttonItalic.isSelected()) {
                    estiloLetra = 2;
                }

                labelTextoPrueba.setFont(new Font(tipoLetra, estiloLetra, dimensionesLetra));
            }
        });

        this.spinnerDimensionesLetras.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Integer valorMinimo = (Integer) modeloSpinnerDimenisones.getMinimum();
                Integer valorMaximo = (Integer) modeloSpinnerDimenisones.getMaximum();
                Integer valorActual = (Integer) spinnerDimensionesLetras.getValue();

                if (valorMinimo.intValue() + 1 > valorActual.intValue()) {
                    spinnerDimensionesLetras.setValue(valorMaximo - 1);
                } else if (valorMaximo.intValue() - 1 < valorActual.intValue()) {
                    spinnerDimensionesLetras.setValue(valorMinimo + 1);
                }

                // Llama al método que realiza los cambios de fuente
                realizarCambios();
            }

            private void realizarCambios() {
                int estiloLetra = 0;
                String tipoLetra = (String) boxTiposLetras.getSelectedItem();
                int dimensionesLetra = (int) spinnerDimensionesLetras.getValue();

                if (buttonBold.isSelected()) {
                    estiloLetra = 1;
                }
                if (buttonNormal.isSelected()) {
                    estiloLetra = 0;
                }
                if (buttonItalic.isSelected()) {
                    estiloLetra = 2;
                }

                labelTextoPrueba.setFont(new Font(tipoLetra, estiloLetra, dimensionesLetra));
            }

        });

        this.listColoresLetras.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int indiceSeleccionado = listColoresLetras.getSelectedIndex();
                    if (indiceSeleccionado != -1) {
                        Colores colorSeleccionado = colores[indiceSeleccionado];
                        Color color = new Color(colorSeleccionado.getRojo(), colorSeleccionado.getVerde(), colorSeleccionado.getAzul());
                        labelTextoPrueba.setForeground(color);
                        sliderRTexto.setValue(colorSeleccionado.getRojo());
                        sliderGTexto.setValue(colorSeleccionado.getVerde());
                        sliderBTexto.setValue(colorSeleccionado.getAzul());
                    }
                }
            }
        });
        this.listaColoresFondo.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int indiceSeleccionado = listaColoresFondo.getSelectedIndex();
                    if (indiceSeleccionado != -1) {
                        Colores colorSeleccionado = colores[indiceSeleccionado];
                        Color color = new Color(colorSeleccionado.getRojo(), colorSeleccionado.getVerde(), colorSeleccionado.getAzul());
                        panelPruebas.setBackground(color);
                        sliderRFondo.setValue(colorSeleccionado.getRojo());
                        sliderGFondo.setValue(colorSeleccionado.getVerde());
                        sliderBFondo.setValue(colorSeleccionado.getAzul());
                    }
                }
            }
        });

        this.sliderRTexto.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    int valueG = sliderGTexto.getValue();
                    int valueB = sliderBTexto.getValue();
                    Color color = new Color(value, valueG, valueB);
                    labelTextoPrueba.setForeground(color);
                }
            }
        });

        this.sliderGTexto.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    int valueR = sliderRTexto.getValue();
                    int valueB = sliderBTexto.getValue();
                    Color color = new Color(valueR, value, valueB);
                    labelTextoPrueba.setForeground(color);
                }
            }
        });

        this.sliderBTexto.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    int valueG = sliderGTexto.getValue();
                    int valueR = sliderRTexto.getValue();
                    Color color = new Color(valueR, valueG, value);
                    labelTextoPrueba.setForeground(color);
                }
            }
        });

        this.sliderRFondo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    int valueG = sliderGFondo.getValue();
                    int valueB = sliderBFondo.getValue();
                    Color color = new Color(value, valueG, valueB);
                    panelPruebas.setBackground(color);
                }
            }
        });

        this.sliderGFondo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    int valueR = sliderRFondo.getValue();
                    int valueB = sliderBFondo.getValue();
                    Color color = new Color(valueR, value, valueB);
                    panelPruebas.setBackground(color);
                }
            }
        });

        this.sliderBFondo.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    int valueG = sliderGFondo.getValue();
                    int valueR = sliderRFondo.getValue();
                    Color color = new Color(valueR, valueG, value);
                    panelPruebas.setBackground(color);
                }
            }
        });

        this.buttonBold.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoLetra = (String) boxTiposLetras.getSelectedItem();
                int dimensionesLetra = (int) spinnerDimensionesLetras.getValue();

                labelTextoPrueba.setFont(new Font(tipoLetra, Font.BOLD, dimensionesLetra));
            }
        });

        this.buttonNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoLetra = (String) boxTiposLetras.getSelectedItem();
                int dimensionesLetra = (int) spinnerDimensionesLetras.getValue();

                labelTextoPrueba.setFont(new Font(tipoLetra, Font.PLAIN, dimensionesLetra));
            }
        });

        this.buttonItalic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoLetra = (String) boxTiposLetras.getSelectedItem();
                int dimensionesLetra = (int) spinnerDimensionesLetras.getValue();

                labelTextoPrueba.setFont(new Font(tipoLetra, Font.ITALIC, dimensionesLetra));
            }
        });

    }
    

    public void mostrar() {
        this.ventana.setVisible(true);
    }

    public static void main(String[] args) {
        new FuentesLetras();
    }
}
