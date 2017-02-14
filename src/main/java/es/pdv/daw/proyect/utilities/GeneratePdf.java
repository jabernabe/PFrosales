package es.pdv.daw.proyect.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import es.pdv.daw.proyect.beans.AlbaranPdf;
import es.pdv.daw.proyect.beans.AlbaranValidate;
import es.pdv.daw.proyect.dao.EmpresaRepository;
import es.pdv.daw.proyect.entity.DetalleAlbaran;

/**
 * Clase que gestiona la creacion de archivos pdf basados en albaranes.
 * 
 * @author jabd.
 *
 */
@Component
public class GeneratePdf {

	/**
	 * Propiedad que encapsula la instancia de Logger de la aplicacion.
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Propiedad que encapsula la ruta de recursos del servidor.
	 */
	private String path = System.getProperty("catalina.home") + File.separator + "work" + File.separator + "Tomcat"
			+ File.separator + "localhost" + File.separator;

	/**
	 * Propiedad que encapsula la fuente a utilizar en el albaran.
	 */
	private Font fuente;

	/**
	 * Constructor sin parametros.
	 */
	public GeneratePdf() {

	}

	/**
	 * Metodo que genera un archivo de pedido en formato pdf.
	 * 
	 * @param albaranValidate
	 * @param empresaRepository
	 */
	public AlbaranPdf generaPdf(AlbaranValidate albaranValidate, AlbaranPdf albaranPdf,
			EmpresaRepository empresaRepository) {

		fuente = new Font();
		fuente.setSize(14);
		fuente.setStyle(Font.BOLD);

		String file = path + albaranValidate.getAlbaran().getIdAlbaran()
				+ albaranValidate.getAlbaran().getCliente().getNombreCliente() + ".pdf";

		generaLogo(empresaRepository);

		if (creaAlbaran(albaranValidate, file, empresaRepository)) {

			File archivo = new File(file);

			if (archivo.exists()) {

				try {
					byte[] bytes = Files.readAllBytes(new File(file).toPath());
					albaranPdf.setFilePdf(DatatypeConverter.printBase64Binary(bytes));
					albaranPdf.setCreated(true);
					albaranPdf.setMessage("Archivo creado correctamente");
					albaranPdf.setPdfName(albaranValidate.getAlbaran().getIdAlbaran()
							+ albaranValidate.getAlbaran().getCliente().getNombreCliente() + ".pdf");

				} catch (IOException e) {

					logger.error(e.getMessage());
				}
			} else {

				albaranPdf.setCreated(false);
				albaranPdf.setMessage("Error al generar el albaran " + albaranValidate.getAlbaran().getIdAlbaran()
						+ albaranValidate.getAlbaran().getCliente().getNombreCliente() + ".pdf");
			}
		} else {

			albaranPdf.setCreated(false);
			albaranPdf.setMessage("Error al generar el albaran " + albaranValidate.getAlbaran().getIdAlbaran()
					+ albaranValidate.getAlbaran().getCliente().getNombreCliente() + ".pdf");
		}

		return albaranPdf;
	}

	/**
	 * Metodo que genera el albaran.
	 * 
	 * @param albaranValidate
	 * @param file
	 * @param empresaRepository
	 * @return
	 */
	private boolean creaAlbaran(AlbaranValidate albaranValidate, String file, EmpresaRepository empresaRepository) {

		boolean generado = false;
		Document document = new Document(PageSize.LETTER, 36, 36, 140, 36);
		PdfWriter writer;

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(file));
			GenerateMembretePdf encabezado = new GenerateMembretePdf(albaranValidate, empresaRepository);

			// indicamos que objecto manejara los eventos al escribir el Pdf
			writer.setPageEvent(encabezado);

			document.open(); // Se abre el documento.

			// Se obtienen las celdas que conformaran la cabecera de la tabla.
			PdfPCell codigo = creaCeldaCodigo();
			PdfPCell articulo = creaCeldaArticulo();
			PdfPCell unidades = creaCeldaUnidades();

			// Se crea la tabla y se añade la cabecera.
			PdfPTable tableDetalle = new PdfPTable(3);
			tableDetalle.addCell(codigo);
			tableDetalle.addCell(articulo);
			tableDetalle.addCell(unidades);
			tableDetalle.setTotalWidth(530f); // ancho de la tabla.
			tableDetalle.setSpacingAfter(50f);
			tableDetalle.setHeaderRows(0);
			tableDetalle.setPaddingTop(700f);

			// Variables para gestionar albaranes de mas de una pagina.
			int totalLineas = albaranValidate.getListaDetalle().size();
			int contador = 0;
			int numInicio = 1;
			int numFinal = 19;
			int paginas = 0;

			// Se añaden los articulos.
			for (DetalleAlbaran detalle : albaranValidate.getListaDetalle()) {

				Paragraph paragraphCodigo = new Paragraph(Integer.toString(detalle.getRosal().getIdRosal()));
				PdfPCell cellCodigo = new PdfPCell(paragraphCodigo);
				cellCodigo.setPaddingRight(80);
				cellCodigo.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableDetalle.addCell(cellCodigo);

				Paragraph paragraphArticulo = new Paragraph(detalle.getRosal().getNombreRosal());
				PdfPCell cellArticulo = new PdfPCell(paragraphArticulo);
				cellArticulo.setPaddingLeft(5);
				tableDetalle.addCell(cellArticulo);

				Paragraph paragraphUnidades = new Paragraph(Integer.toString(detalle.getCantidad()));
				PdfPCell cellUnidades = new PdfPCell(paragraphUnidades);
				cellUnidades.setPaddingRight(80);
				;
				cellUnidades.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tableDetalle.addCell(cellUnidades);

				contador++;

				if (contador > numFinal) {
					// numFinal = contador+18;
					tableDetalle.writeSelectedRows(0, -1, 0, 1, 40f, 487f, writer.getDirectContent());
					tableDetalle.writeSelectedRows(0, -1, numInicio, numFinal, 40f, 463f, writer.getDirectContent());
					paginas++;
					document.newPage();
					numFinal = contador + 18;
					numInicio = contador - 1;
				}
			}

			if (paginas > 0) { // Se añaden registros ultima pagina.

				int registroInicio = paginas * 19;
				tableDetalle.writeSelectedRows(0, -1, 0, 1, 40f, 487f, writer.getDirectContent());
				tableDetalle.writeSelectedRows(0, -1, registroInicio, totalLineas + 1, 40f, 463f,
						writer.getDirectContent());
			}

			// Se añaden los articulos si el albaran no excede de un pagina.
			if (totalLineas <= 19) {

				tableDetalle.writeSelectedRows(0, -1, 40f, 487f, writer.getDirectContent());
			}

			PdfPTable tableFooter = creaPieAlbaran();
			tableFooter.writeSelectedRows(0, -1, 40f, 130f, writer.getDirectContent());

			document.close();
			generado = true;
		} catch (FileNotFoundException e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
		} catch (DocumentException e1) {
			logger.info(e1.getMessage());
			e1.printStackTrace();
		}

		return generado;
	}

	/**
	 * Metodo que devuelve la tabla del pie del albaran.
	 * 
	 * @return
	 */
	private PdfPTable creaPieAlbaran() {

		PdfPTable tableFooter = new PdfPTable(1);
		tableFooter.setTotalWidth(530f); // ancho de la tabla.
		Paragraph parrafo = new Paragraph("CONFORME", fuente);
		PdfPCell celdaTitulo = new PdfPCell(parrafo);
		celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		PdfPCell celdaCuerpo = new PdfPCell(new Paragraph(" " + "\n" + "\n" + "\n" + "\n" + "\n"));
		tableFooter.addCell(celdaTitulo);
		tableFooter.addCell(celdaCuerpo);

		return tableFooter;
	}

	/**
	 * Metodo que devuelve la celda de encabezado codigo de la tabla detalle.
	 * 
	 * @return
	 */
	private PdfPCell creaCeldaCodigo() {

		Paragraph parrafoCodigo = new Paragraph("Código", fuente);
		PdfPCell celdaCodigo = new PdfPCell(parrafoCodigo);
		celdaCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaCodigo.setBackgroundColor(new BaseColor(213, 219, 219));
		celdaCodigo.setPadding(5);

		return celdaCodigo;
	}

	/**
	 * Metodo que devuelve la celda de encabezado articulo de la tabla detalle.
	 * 
	 * @return
	 */
	private PdfPCell creaCeldaArticulo() {

		Paragraph parrafoArticulo = new Paragraph("Artículo", fuente);
		PdfPCell celdaArticulo = new PdfPCell(parrafoArticulo);
		celdaArticulo.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaArticulo.setBackgroundColor(new BaseColor(213, 219, 219));
		celdaArticulo.setPadding(5);

		return celdaArticulo;
	}

	/**
	 * Metodo que devuelve la celda de encabezado unidades de la tabla detalle.
	 * 
	 * @return
	 */
	private PdfPCell creaCeldaUnidades() {

		Paragraph parrafoUnidades = new Paragraph("Unidades", fuente);
		PdfPCell celdaUnidades = new PdfPCell(parrafoUnidades);
		celdaUnidades.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaUnidades.setBackgroundColor(new BaseColor(213, 219, 219));
		celdaUnidades.setPadding(5);

		return celdaUnidades;
	}

	/**
	 * Metodo que sube el logo de la empresa al servidor.
	 * 
	 * @param empresaRepository
	 */
	private void generaLogo(EmpresaRepository empresaRepository) {

		// Se comprueba si la imagen existe en el servidor.
		boolean existeImagen = compruebaImagen();

		if (!existeImagen) { // Si no existe.

			try {
				int num = (int) empresaRepository.count();

				if (num > 0) {

					// Se obtiene la imagen de la bd y se guarda en el servidor.
					byte[] contenido = empresaRepository.findByIdEmpresa(1).getImagenLogo();
					File file = new File(path + "logoAlbaran.jpg");
					FileOutputStream fileOutputStream = new FileOutputStream(file);
					fileOutputStream.write(contenido);
					fileOutputStream.close();

				}

			} catch (NullPointerException ex) {
				logger.error(ex.getMessage());
				ex.getMessage();
			} catch (FileNotFoundException e1) {
				logger.error("Archivo de logo no encontrado");
				e1.getMessage();
			} catch (IOException e1) {
				logger.error("Error al generar logo: " + e1.getMessage());
				e1.getMessage();
			}
		}

	}

	/**
	 * Metodo que comprueba si el logo existe en el servidor.
	 * 
	 * @return
	 */
	private boolean compruebaImagen() {

		boolean existe = false;

		File archivo = new File(path + "logoAlbaran.jpg");
		if (archivo.exists()) {
			existe = true;
		}

		return existe;
	}

	// Metodos setter y getter.

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Font getFuente() {
		return fuente;
	}

	public void setFuente(Font fuente) {
		this.fuente = fuente;
	}

	public Logger getLogger() {
		return logger;
	}

}
