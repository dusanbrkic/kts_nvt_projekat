package gradjanibrzogbroda.backend.domain;

import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import gradjanibrzogbroda.backend.dto.ZaposleniDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "zaposleni")
@Inheritance(strategy=InheritanceType.JOINED)
@SQLDelete(sql
        = "UPDATE zaposleni "
        + "SET obrisan = true "
        + "WHERE id = ?")
@Where(clause = "obrisan = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Zaposleni implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ime", nullable = false)
    private String ime;
    @Column(name = "prezime", nullable = false)
    private String prezime;
    @Column(name = "pol", nullable = false)
    private Pol pol;
    @Column(name = "datum_rodjenja", nullable = false)
    private LocalDate datumRodjenja;
    @Column(name = "trenutna_plata", nullable = false)
    private Double trenutnaPlata;
    @Column(name = "naziv_slike", nullable = false)
    private String nazivSlike;

    @OneToMany(mappedBy = "zaposleni", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plata> plate=new ArrayList<Plata>();


    @Column(name = "obrisan", nullable = false)
    private boolean obrisan;
    
    @Column(name = "username", nullable = false,unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "zaposleni_role",
            joinColumns = @JoinColumn(name = "zaposleni_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
	
	@Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate; 
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

//    public Zaposleni(ZaposleniDTO zDto){
//        updateFields(zDto);
//    }

    public void updateFields(ZaposleniDTO zDto, Role role){
        ime = zDto.getIme();
        prezime = zDto.getPrezime();
        pol = zDto.getPol();
        datumRodjenja = zDto.getDatumRodjenja();
        trenutnaPlata = zDto.getTrenutnaPlata();
        roles = new ArrayList<>();
		roles.add(role);
        username = zDto.getUsername();
        nazivSlike = generateProfilePicName();
    }

    public String generateProfilePicName(){
        return this.username + ".jpg";
    }
}
