import styled from "styled-components";
import { motion } from "framer-motion";

const Container = styled(motion.div)`
  box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;
  background-color: ${({ theme }) => theme.widgetColor};
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 1rem 2rem;
  width: 85%;
  margin-bottom: 1rem;
  /* margin-left: 3rem; */
`;

const Title = styled.h3`
  font-size: 1.5rem;
  font-weight: 600;
  margin: 0;
  width: fit-content;
`;

const CloseButton = styled.div`
  display: flex;
  align-items: center;
`;

const StudyPlanner = ({ setWidgetId, setIsShow }) => {
  const close = () => {
    setIsShow(false);
  };
  return (
    <Container
      layout
      layoutId="M04"
      whileHover={{
        scale: 1.01,
      }}
      onClick={() => {
        setWidgetId("M04");
      }}
    >
      <Wrapper>
        <Title>오늘의 공부 시간</Title>
        {setIsShow && <CloseButton onClick={close}>❌</CloseButton>}
      </Wrapper>
    </Container>
  );
};

export default StudyPlanner;