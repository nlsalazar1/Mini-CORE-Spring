package com.udla.controllers;

import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.clienteapp.models.entity.InmuebleCantidadSectorDTO;
import com.udla.models.entity.Vendedor;
import com.udla.models.entity.Ventas;
import com.udla.models.service.IVendedorService;
import com.udla.models.service.IVentasService;

@Controller
@RequestMapping("/views/ventas")
public class VentasController {
	
	@Autowired 
	private IVentasService ventasService; //importamos un objeto IClienteService
	
	@Autowired
	private IVendedorService vendedorService;
	
	
	
	@GetMapping("/")
	public String listarClientes(Model model) {
		
		List<Ventas> listadoVentas = ventasService.listarTodos(); //Usamos un metodo de la clase que estan en IClienteService
		
		model.addAttribute("titulo","Lista de Ventas"); //Podemos enviar el titulo de la pagina desde esta clase
		model.addAttribute("ventas", listadoVentas); //Enviamos a la pag el listado de clientes
		return "/views/ventas/listar";
	}
	
	@GetMapping("/create") 
	public String crear(Model model){
		
		Ventas ventas = new Ventas();
		List<Vendedor> listVendedores = vendedorService.listaVendedores();
		
		model.addAttribute("titulo", "Formulario: Nueva Venta"); //Enviamos informacion al formulario
		model.addAttribute("ventas", ventas);
		model.addAttribute("vendedores", listVendedores);
		
		return "/views/ventas/frmCrear";
	}
	
	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Ventas ventas, BindingResult result, //@BindigResult captura los errores del formulario 
			Model model, RedirectAttributes attribute) { //RedirectAttributes utilizamos para dar los mensajes de exito o error de la plantilla - video 14 crud
		
		List<Vendedor> listVendedores = vendedorService.listaVendedores();
		
		if(result.hasErrors()) {
			
			
			model.addAttribute("titulo", "Formulario: Nueva Venta"); //Enviamos informacion al formulario
			model.addAttribute("ventas", ventas);
			model.addAttribute("vendedores", listVendedores);
			System.out.println("Existieron errores en el formulario..!!!");
			
			return "/views/ventas/frmCrear";
		}
		
		ventasService.guardar(ventas);
		
		System.out.println("Venta guardada con exito..!!!");
		attribute.addFlashAttribute("success","Venta guardada con exito..!!!");
		return "redirect:/views/clientes/";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idVenta, Model model, RedirectAttributes attribute){
		
		Ventas ventas = null;
		
		if(idVenta > 0) { //video 13 crud asegurar de que exista el cliente
			ventas = ventasService.buscarPorId(idVenta);
			if(ventas == null) {
				System.out.println("Error: El ID de la venta no existe!");
				attribute.addFlashAttribute("error","ATENCIÓN: Error el ID del cliente no existe!!!");

				return "redirect:/views/ventas/";
			}
		}else {
			System.out.println("Error: Error con el ID de la venta...!");
			attribute.addFlashAttribute("error","ATENCIÓN: Error con el ID del cliente!!!");

			return "redirect:/views/ventas/";
		}
		
		List<Vendedor> listVendedores = vendedorService.listaVendedores();
		
		model.addAttribute("titulo", "Formulario: Editar Cliente"); //Enviamos informacion al formulario
		model.addAttribute("ventas", ventas);
		model.addAttribute("vendedores", listVendedores);
		
		return "/views/ventas/frmCrear";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Long idVenta, RedirectAttributes attribute){
		
		Ventas ventas = null;
		
		if(idVenta>0) {
			ventas = ventasService.buscarPorId(idVenta);
			
			if(ventas == null) {
				System.out.println("Error: El ID de la venta no existe!");
				attribute.addFlashAttribute("error","ATENCIÓN: Error el ID del cliente no existe!!!");

				return "redirect:/views/ventas/";
			}
		}else {
			System.out.println("Error: Error con el ID del cliente!");
			attribute.addFlashAttribute("error","ATENCIÓN: Error con el ID de la venta!!!");

			return "redirect:/views/ventas/";
		}				
			
		
		ventasService.eliminar(idVenta);
		System.out.println("El registro se elimino con exito...!!!");
		attribute.addFlashAttribute("warning","ATENCIÓN: El registro se elimino con exito...!!!");

		return "redirect:/views/ventas/";
	}
	
	
	//----------------------------------------------------------------------------------------------------
	
	
	@GetMapping("/mostrarVentas")    //funciona
	public String barGraph(Model model) {
		
		List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector("casa", 200000, 300000);
        
		List<InmuebleCantidadSectorDTO> listaSectores = new ArrayList<>();

		Map<String, BigInteger> surveyMap = new LinkedHashMap<>();

		for (Object[] result : results) {
            String sector = (String) result[0];
            BigInteger cantidad = (BigInteger) result[1];
            
            
            surveyMap.put(sector, cantidad);
            //InmuebleCantidadSectorDTO inmuebleCantidadSectorDTO = new InmuebleCantidadSectorDTO(sector, cantidad);
            //listaSectores.add(inmuebleCantidadSectorDTO);
        }
		
		model.addAttribute("surveyMap", surveyMap);
		
		return "/views/analisis/AnalisisGraficas";
	}
	
	
	@GetMapping("/displayVentas/{tipo}/{valor_min}/{valor_max}")
	public String barGraph(@PathVariable("tipo") String tipo, @PathVariable("valor_min") float valor_min, @PathVariable("valor_max") float valor_max, Model model) {
		List<Object[]> results = publicacionService.obtenerCantidadInmueblesConSector("casa", 200000, 300000);
        
		List<InmuebleCantidadSectorDTO> listaSectores = new ArrayList<>();

		Map<String, BigInteger> surveyMap = new LinkedHashMap<>();

		for (Object[] result : results) {
            String sector = (String) result[0];
            BigInteger cantidad = (BigInteger) result[1];
            
            
            surveyMap.put(sector, cantidad);
            //InmuebleCantidadSectorDTO inmuebleCantidadSectorDTO = new InmuebleCantidadSectorDTO(sector, cantidad);
            //listaSectores.add(inmuebleCantidadSectorDTO);
        }
		
		model.addAttribute("surveyMap", surveyMap);

	    return "/views/analisis/AnalisisGraficas";
	}
	
}