package services;

import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;

@ProvidedServices
({
    @ProvidedService(name="sum", type=ISumService.class, implementation=@Implementation(SumService.class))
})
@Agent
public class SumAgent {

}
