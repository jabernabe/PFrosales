package es.pdv.daw.proyect.utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import es.pdv.daw.proyect.beans.AlbaranValidate;
import es.pdv.daw.proyect.dao.EmpresaRepository;
import es.pdv.daw.proyect.entity.Empresa;

/**
 * Clase que gestiona la creacion del membrete de los albaranes.
 * 
 * @author jabd.
 *
 */
public class GenerateMembretePdf extends PdfPageEventHelper {

	/**
	 * Propiedad que encapsula la instancia de Logger de la aplicacion.
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Propiedad que encapsula la imagen con el logo de la empresa.
	 */
	private Image imagen;

	/**
	 * Propiedad que encapsula la tabla con los datos de la empresa.
	 */
	private PdfPTable tableEmpresa;

	/**
	 * Propiedad que encapsula la tabla con el numero y fecha del albaran.
	 */
	private PdfPTable tableNumAlbaran;

	/**
	 * Propiedad que encapsula la tabla con los datos del cliente.
	 */
	private PdfPTable tableCliente;

	/**
	 * Propiedad que encapsula la ruta de recursos del servidor.
	 */
	private String path = System.getProperty("catalina.home") + File.separator + "work" + File.separator + "Tomcat"
			+ File.separator + "localhost" + File.separator;

	/**
	 * Constructor de la clase.
	 * 
	 * @param albaranValidate
	 * @param empresaRepository2
	 */
	public GenerateMembretePdf(AlbaranValidate albaranValidate, EmpresaRepository empresaRepository) {

		imagen = damelogoEmpresa();
		tableEmpresa = creaDatosEmpresa(empresaRepository);
		tableNumAlbaran = creaDatosAlbaran(albaranValidate);
		tableCliente = creaDatosCliente(albaranValidate);
	}

	/**
	 * Metodo que devuelve la imagen con el logo de la empresa.
	 * 
	 * @return
	 */
	private Image damelogoEmpresa() {

		Image imagen = null;

		try {

			imagen = Image.getInstance(path + "logoAlbaran.jpg");
			imagen.setAbsolutePosition(40, 650f);
			imagen.setAlignment(Image.LEFT | Image.TEXTWRAP);
			imagen.scalePercent(35f);
			imagen.setIndentationLeft(40f);
		} catch (BadElementException e) {
			logger.error(e.getMessage());
			e.getMessage();
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
			e.getMessage();
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.getMessage();
		}

		return imagen;
	}

	/**
	 * Metodo que devuelve una tabla con los datos de la empresa.
	 * 
	 * @param empresaRepository
	 * @return
	 */
	private PdfPTable creaDatosEmpresa(EmpresaRepository empresaRepository) {

		Font fuente = new Font();
		fuente.setSize(14);
		fuente.setStyle(Font.BOLD);

		PdfPTable tableEmpresa = new PdfPTable(1);

		// Se crean las frases del encabezado con los datos de la empresa.

		int num = (int) empresaRepository.count();

		if (num > 0) {

			Empresa empresa = empresaRepository.findOne(1);

			Phrase nombreEmpresa = new Phrase(empresa.getNombreEmpresa() + "\n", fuente);
			Phrase domicilio = new Phrase(empresa.getDireccion() + "\n");
			Phrase poblacion = new Phrase(empresa.getMunicipio() + "\n");
			Phrase provincia = new Phrase(empresa.getProvincia() + "\n");
			Phrase nif = new Phrase(empresa.getTipoIden() + ": " + empresa.getIdentificador());

			// Se crea el parrafo del encabezado de la empresa.
			Paragraph encabezado = new Paragraph();
			encabezado.setLeading(15); // Interlineado.
			encabezado.add(nombreEmpresa);
			encabezado.add(domicilio);
			encabezado.add(poblacion);
			encabezado.add(provincia);
			encabezado.add(nif);

			// Se crea una celda con un padding de 10 y se le añade el
			// encabezado.
			PdfPCell celda1 = new PdfPCell();
			celda1.setPadding(10);
			celda1.addElement(encabezado);
			celda1.setBorder(0);

			// Se agregan las celdas a las tablas.
			tableEmpresa.addCell(celda1);
			tableEmpresa.setTotalWidth(250f);
			tableEmpresa.setSpacingAfter(50f);

		}

		return tableEmpresa;
	}

	/**
	 * Metodo que devuelve un tabla con el numero de pedido y fecha.
	 * 
	 * @param albaranValidate
	 * @return
	 */
	private PdfPTable creaDatosAlbaran(AlbaranValidate albaranValidate) {

		// Se crea la fuente de tamaño 14 en necrita.
		Font fuente = new Font();
		fuente.setSize(14);
		fuente.setStyle(Font.BOLD);

		// Se crea la frase con el numero de albaran y se añade a la celda.
		Phrase numeroAlbaran = new Phrase("Albarán Nº: " + albaranValidate.getAlbaran().getIdAlbaran(), fuente);
		PdfPCell celdaNumAlbaran = new PdfPCell(numeroAlbaran);
		celdaNumAlbaran.setPadding(10);
		celdaNumAlbaran.setBackgroundColor(new BaseColor(213, 219, 219));

		// Se cre cambia formato fecha, se crea la frase y se añade a la celda.
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		String fecha = formateador.format(albaranValidate.getAlbaran().getFecha());
		Phrase fechaAlbaran = new Phrase("Fecha: " + fecha, fuente);
		PdfPCell celdaFechaAlbaran = new PdfPCell(fechaAlbaran);
		celdaFechaAlbaran.setPadding(10);
		celdaFechaAlbaran.setBackgroundColor(new BaseColor(213, 219, 219));

		// Se crea la tabla y se añaden las celdas.
		PdfPTable tableNumAlbaran = new PdfPTable(2);
		tableNumAlbaran.addCell(celdaNumAlbaran);
		tableNumAlbaran.addCell(celdaFechaAlbaran);
		tableNumAlbaran.setTotalWidth(530f);
		tableNumAlbaran.setSpacingAfter(50f);

		return tableNumAlbaran;
	}

	/**
	 * Metodo que devuelve la tabla con los datos del cliente.
	 * 
	 * @param albaranValidate
	 * @return
	 */
	private PdfPTable creaDatosCliente(AlbaranValidate albaranValidate) {

		// Se crea la fuente de tamaño 14 en necrita.
		Font fuente = new Font();
		fuente.setSize(14);
		fuente.setStyle(Font.BOLD);

		// Se crea la frase del titulo, el parrafo y se añade a la celda.
		Phrase tituloTablaCliente = new Phrase("Cliente", fuente);
		Paragraph tituloCliente = new Paragraph();
		tituloCliente.add(tituloTablaCliente);
		tituloCliente.setAlignment(Element.ALIGN_CENTER);

		PdfPCell celdaTituloCliente = new PdfPCell();
		celdaTituloCliente.addElement(tituloCliente);

		celdaTituloCliente.setPaddingBottom(5);
		celdaTituloCliente.setBackgroundColor(new BaseColor(213, 219, 219));

		// Se crean las frases con los datos del cliente, el parrafo y se añade
		// a la celda.
		Phrase nombreCliente = new Phrase(
				"Cliente: " + albaranValidate.getAlbaran().getCliente().getNombreCliente() + "\n");

		Phrase domicilioCliente = new Phrase("Domicilio: " + albaranValidate.getAlbaran().getCliente().getDireccion()
				+ "   " + albaranValidate.getAlbaran().getCliente().getCp() + "\n");

		Phrase ciudad = new Phrase("Ciudad: " + albaranValidate.getAlbaran().getCliente().getMunicipio() + "  ("
				+ albaranValidate.getAlbaran().getCliente().getProvincia() + ")" + "\n");

		Phrase identificadorCliente = new Phrase(albaranValidate.getAlbaran().getCliente().getTipoIden() + ": "
				+ albaranValidate.getAlbaran().getCliente().getIdentificador() + "\n");
		// Se crea el parrafo y se añaden las frases.
		Paragraph datosCliente = new Paragraph();
		datosCliente.add(nombreCliente);
		datosCliente.add(domicilioCliente);
		datosCliente.add(ciudad);
		datosCliente.add(identificadorCliente);

		// Se crea la celda y se añade el parrafo.
		PdfPCell celdaDatosCliente = new PdfPCell();
		celdaDatosCliente.addElement(datosCliente);
		celdaDatosCliente.setPadding(10);

		// Se crea la tabla y se añaden las celdas.
		PdfPTable tableCliente = new PdfPTable(1);
		tableCliente.addCell(celdaTituloCliente);
		tableCliente.addCell(celdaDatosCliente);
		tableCliente.setTotalWidth(530f); // tamaño de tabla.
		tableCliente.setSpacingAfter(50f);

		return tableCliente;
	}

	/**
	 * Manejador del evento onEndPage, usado para generar el encabezado
	 */
	public void onEndPage(PdfWriter writer, Document document) {

		try {

			document.add(imagen);
			tableEmpresa.writeSelectedRows(0, -1, 330f, 750f, writer.getDirectContent());
			tableNumAlbaran.writeSelectedRows(0, -1, 40f, 640f, writer.getDirectContent());
			tableCliente.writeSelectedRows(0, -1, 40f, 600f, writer.getDirectContent());

		} catch (DocumentException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

	}

	// GETTER Y SETTER

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}

	public PdfPTable getTableEmpresa() {
		return tableEmpresa;
	}

	public void setTableEmpresa(PdfPTable tableEmpresa) {
		this.tableEmpresa = tableEmpresa;
	}

	public PdfPTable getTableNumAlbaran() {
		return tableNumAlbaran;
	}

	public void setTableNumAlbaran(PdfPTable tableNumAlbaran) {
		this.tableNumAlbaran = tableNumAlbaran;
	}

	public PdfPTable getTableCliente() {
		return tableCliente;
	}

	public void setTableCliente(PdfPTable tableCliente) {
		this.tableCliente = tableCliente;
	}

}
