package shortinterest.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import shortinterest.domain.Company;
import shortinterest.domain.ShortPosition;
import shortinterest.domain.ShortPositionPK;

@Repository
public interface ShortPositionRepository  extends PagingAndSortingRepository<ShortPosition, ShortPositionPK> {
}
