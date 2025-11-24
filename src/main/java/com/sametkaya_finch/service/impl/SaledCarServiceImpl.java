package com.sametkaya_finch.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sametkaya_finch.dto.CurrencyRatesResponse;
import com.sametkaya_finch.dto.DtoCar;
import com.sametkaya_finch.dto.DtoCustomer;
import com.sametkaya_finch.dto.DtoGallerist;
import com.sametkaya_finch.dto.DtoSaledCar;
import com.sametkaya_finch.dto.DtoSaledCarIU;
import com.sametkaya_finch.entity.Car;
import com.sametkaya_finch.entity.Customer;
import com.sametkaya_finch.entity.SaledCar;
import com.sametkaya_finch.enums.CarStatusType;
import com.sametkaya_finch.exception.BaseException;
import com.sametkaya_finch.exception.ErrorMessage;
import com.sametkaya_finch.exception.MessageType;
import com.sametkaya_finch.repository.CarRepository;
import com.sametkaya_finch.repository.CustomerRepository;
import com.sametkaya_finch.repository.GalleristRepository;
import com.sametkaya_finch.repository.SaledCarRepository;
import com.sametkaya_finch.service.ICurrencyRatesService;
import com.sametkaya_finch.service.ISaledCarService;
import com.sametkaya_finch.utils.DateUtils;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private GalleristRepository galleristRepository;

	@Autowired
	private ICurrencyRatesService currencyRatesService;

	@Autowired
	private SaledCarRepository saledCarRepository;

	public BigDecimal convertCustomerAmountToUSD(Customer customer) {

		// sorgunun yapildigi zamanin tarihi verilmeli
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService
				.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));

		// string donuyordu cevirdik
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

		// mevcut parayi dolar kuruna bolduk dolar olarak musteri parasi elimizde
		BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);

		return customerUSDAmount;

	}

	public boolean checkCarStatus(Long carId) {
		Optional<Car> optCar = carRepository.findById(carId);
		if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			return false;
		}
		return true;
	}

	// musterinin satin alacak parasi var mi yok mu

	public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {

		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
		if (optCustomer.isEmpty()) {
			throw new BaseException(
					new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		}

		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
		if (optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
		}

		// musteriyi verdik ve parasini dolara cevirdik
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());

		if (customerUSDAmount.compareTo(optCar.get().getPrice()) == 0
				|| customerUSDAmount.compareTo(optCar.get().getPrice()) > 0) {
			return true;
		}

		return false;
	}

	// musterinin ne kadar parasi kaldi tlye cevirelim
	public BigDecimal remainingCustomerAmount(Customer customer, Car car) {
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
		BigDecimal remainingCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

		CurrencyRatesResponse currencyRatesResponse = currencyRatesService
				.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));

		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

		return remainingCustomerUSDAmount.multiply(usd);
	}

	// dbye arabanin satildigi kaydedilecek
	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar = new SaledCar();
		saledCar.setCreateTimeDate(new Date());

		// bulursa donecek bulamazsa null setleyecek opt kullanmadan kisayol
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

		return saledCar;

	}

	@Override
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {

		if (!checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
		}
		if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
			throw new BaseException(
					new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
		}

		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));

		// araba satildi car tablosundaki carstatustype saled cevrilmeli

		Car car = savedSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);

		// hazir update metodu yok ama save varsa uzerine yazar bu yuzden kullanilabilir
		carRepository.save(car);

		Customer customer = savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remainingCustomerAmount(customer, car));

		customerRepository.save(customer);

		return toDto(savedSaledCar);
	}

	public DtoSaledCar toDto(SaledCar saledCar) {
		DtoSaledCar dtoSaledCar = new DtoSaledCar();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();

		BeanUtils.copyProperties(saledCar, dtoSaledCar);
		BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(saledCar.getCar(), dtoCar);

		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		dtoSaledCar.setCar(dtoCar);

		return dtoSaledCar;

	}

}
