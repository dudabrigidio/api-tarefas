package br.com.fiap.resource;

import br.com.fiap.bo.TarefaBO;
import br.com.fiap.to.TarefaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/tarefas")
public class TarefaResource {


    private TarefaBO tarefaBO = new TarefaBO();


    @POST
    @Path("/inserir")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir (@QueryParam("ordem") int ordem, @Valid TarefaTO tarefa){
        System.out.println(ordem);
        TarefaTO resultado = tarefaBO.inserir(ordem, tarefa);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED
        } else {
            response = Response.status(404); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @PUT
    @Path("/alterar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar (@Valid TarefaTO tarefa, @QueryParam("idTarefa") int idTarefa){
        String resultado = tarefaBO.alterar(tarefa, idTarefa);
        Response.ResponseBuilder response = null;
        if (resultado != null) {

            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }


    @PUT
    @Path("/setOrdem")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setOrdem (@QueryParam("ordem") int ordem, @QueryParam("idTarefa") int idTarefa){
        System.out.println("Recebido ordem: " + ordem + ", idTarefa: " + idTarefa);
        String resultado = tarefaBO.setOrdem(ordem, idTarefa);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok();
        } else {

            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }



    @DELETE
    @Path("/excluir")
    public Response excluir (@QueryParam("idTarefa") int idTarefa){
        String resultado = tarefaBO.excluir(idTarefa);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }


    @GET
    @Path("/id")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTarefaById(@QueryParam("idTarefa") int idTarefa) {
        TarefaTO resultado = tarefaBO.getTarefaById(idTarefa);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();

    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTarefas(){
        ArrayList<TarefaTO> resultado = tarefaBO.listarTarefas();
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok();
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();

    }

}
