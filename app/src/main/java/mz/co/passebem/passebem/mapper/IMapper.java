package mz.co.passebem.passebem.mapper;

public interface IMapper<From, To> {

    To map(From from);
}