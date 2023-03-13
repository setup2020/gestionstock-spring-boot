package cm.aupas.gestionstock.dto;

import cm.aupas.gestionstock.domain.Company;
import cm.aupas.gestionstock.domain.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Embedded;
import java.util.List;

@Data
@Builder
public class CompanyDto {
    private Long id;
    List<User> users;
    private String name;
    @Embedded
    private AddressDto address;
    private String codeFiscal;
    private String email;
    private String phone;
    private String webSite;
    private String description;

    public static CompanyDto fromEntity(Company company){
        if(company==null){
            return null;
        }

        return CompanyDto.builder()
                .id(company.getId())
                .address(AddressDto.fromEntity(company.getAddress()))
                .codeFiscal(company.getCodeFiscal())
                .email(company.getEmail())
                .phone(company.getPhone())
                .webSite(company.getWebSite())
                .description(company.getDescription())
                .build();
    }

    public static Company toEntity(CompanyDto companyDto){
        if(companyDto==null){
            return null;
        }
        Company company=new Company();
        company.setAddress(AddressDto.toEntity(companyDto.getAddress()));
        company.setEmail(companyDto.getEmail());
        company.setName(companyDto.getName());
        company.setPhone(company.getPhone());
        company.setDescription(company.getDescription());
        company.setCodeFiscal(company.getCodeFiscal());
        company.setWebSite(companyDto.getWebSite());
        return company;

    }
}
