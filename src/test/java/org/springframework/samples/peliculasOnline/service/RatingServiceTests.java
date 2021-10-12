package org.springframework.samples.peliculasOnline.service;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.peliculasOnline.model.Rating;
import org.springframework.samples.peliculasOnline.util.EntityUtils;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class RatingServiceTests {

	@Autowired
	protected RatingService ratingervice;	

	@Test
	void shouldFindRatings() {
		Collection<Rating> ratings = this.ratingervice.findRatings();

		Rating rating = EntityUtils.getById(ratings, Rating.class, 3);
		/*assertThat(vet.getLastName()).isEqualTo("Douglas");
		assertThat(vet.getNrOfSpecialties()).isEqualTo(2);
		assertThat(vet.getSpecialties().get(0).getName()).isEqualTo("dentistry");
		assertThat(vet.getSpecialties().get(1).getName()).isEqualTo("surgery");*/
	}


}
