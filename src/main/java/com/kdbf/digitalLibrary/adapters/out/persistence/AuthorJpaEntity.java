package com.kdbf.digitalLibrary.adapters.out.persistence;

import java.time.Year;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
public class AuthorJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false)
  private UUID publicId;
  @Column(nullable = false)
  private String name;
  @Column
  private Year birthYear;
  @Column
  private Year deathYear;

  @OneToMany(mappedBy = "author")
  private List<BookJpaEntity> books;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((publicId == null) ? 0 : publicId.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AuthorJpaEntity other = (AuthorJpaEntity) obj;
    if (id != other.id)
      return false;
    if (publicId == null) {
      if (other.publicId != null)
        return false;
    } else if (!publicId.equals(other.publicId))
      return false;
    return true;
  }

}
